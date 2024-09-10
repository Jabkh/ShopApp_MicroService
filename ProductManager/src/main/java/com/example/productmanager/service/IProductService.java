package com.example.productmanager.service;

import com.example.productmanager.dto.ProductDtoRequest;
import com.example.productmanager.dto.ProductDtoResponse;

import java.util.List;

public interface IProductService {
    List<ProductDtoResponse> getAllProducts();
    ProductDtoResponse getProductById(int id);
    List<ProductDtoResponse> getProductsByName(String name);
    List<ProductDtoResponse> getProductsByCategory(String category); // Return a list
    ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest);
    ProductDtoResponse updateProduct(int id, ProductDtoRequest productDtoRequest);
    boolean deleteProduct(int id);
}
