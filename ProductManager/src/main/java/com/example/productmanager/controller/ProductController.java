package com.example.productmanager.controller;

import com.example.productmanager.dto.ProductDtoRequest;
import com.example.productmanager.dto.ProductDtoResponse;
import com.example.productmanager.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDtoResponse>> getProductByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.getProductsByName(name));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDtoResponse>> getProductsByCategory(@PathVariable("category") String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDtoResponse> createProduct(@RequestBody ProductDtoRequest productDtoRequest) {
        return ResponseEntity.ok(productService.createProduct(productDtoRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable("id") int id, @RequestBody ProductDtoRequest productDtoRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, productDtoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
