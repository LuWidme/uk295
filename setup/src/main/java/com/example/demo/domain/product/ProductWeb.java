package com.example.demo.domain.product;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductWeb {
    @Operation(summary = "get a product by its ID")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById
            (@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok().body(new Product(productId, "sneakers", 50.20));
    }


    @PostMapping("/{productId}")
    ResponseEntity<String> addProduct(@PathVariable("productId") Integer productId, @RequestBody @Valid Product product) {
        // persisting the Product
        return ResponseEntity.ok("Product is valid");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
