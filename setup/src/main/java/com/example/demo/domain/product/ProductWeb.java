package com.example.demo.domain.product;

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
@RequestMapping("/products")
public class ProductWeb {

    private ProductMapper productMapper;

    @Autowired
    public ProductWeb(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTOWithoutPrice> findById
            (@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok()
                .body(productMapper.productToProductDTOWithoutPrice
                        (new Product(productId,"sneakers",50.20)));
    }


    @GetMapping("/{productId}/2")
    public ResponseEntity<ProductDTOWithPrice> findById2
            (@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok()
                .body(productMapper.productToProductDTOWithPrice(new Product(productId,"sneakers",50.20)));
    }
    @PostMapping("/{productId}")
    ResponseEntity<String> addProduct( @PathVariable("productId") Integer productId, @RequestBody @Valid Product product) {
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
