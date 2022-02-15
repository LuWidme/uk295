package com.example.demo.domain.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HomepageWeb {
    @GetMapping("/")
    public ResponseEntity<String> getHomepageString() {
        return ResponseEntity.ok().body("Hello, This is the Homepage");
    }
}