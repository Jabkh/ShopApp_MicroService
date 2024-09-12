package com.example.productmanager.service.impl;

import com.example.productmanager.dto.ProductDtoRequest;
import com.example.productmanager.dto.ProductDtoResponse;
import com.example.productmanager.model.Product;
import com.example.productmanager.service.IProductService;
import com.example.productmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDtoResponse> getAllProducts() {
        List<ProductDtoResponse> productDtoResponseList = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            productDtoResponseList.add(convertToDto(product));
        });
        return productDtoResponseList;
    }

    @Override
    public ProductDtoResponse getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return convertToDto(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public List<ProductDtoResponse> getProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);
        if (products != null) {
            return products.stream().map(this::convertToDto).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public List<ProductDtoResponse> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products != null) {
            return products.stream().map(this::convertToDto).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest) {
        Product product = Product.builder()
                .name(productDtoRequest.getName())
                .description(productDtoRequest.getDescription())
                .price(productDtoRequest.getPrice())
                .category(productDtoRequest.getCategory())
                .stock(productDtoRequest.getStock())
                .build();
        productRepository.save(product);

        return convertToDto(product);
    }

    @Override
    public ProductDtoResponse updateProduct(int id, ProductDtoRequest productDtoRequest) {
        ProductDtoResponse productDtoResponse = getProductById(id);
        Product product = Product.builder()
                .id(productDtoResponse.getId())
                .name(productDtoRequest.getName())
                .description(productDtoRequest.getDescription())
                .price(productDtoRequest.getPrice())
                .category(productDtoRequest.getCategory())
                .stock(productDtoRequest.getStock())
                .build();
        productRepository.save(product);

        return convertToDto(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        ProductDtoResponse productDtoResponse = getProductById(id);
        Product product = Product.builder()
                .id(productDtoResponse.getId())
                .name(productDtoResponse.getName())
                .description(productDtoResponse.getDescription())
                .price(productDtoResponse.getPrice())
                .category(productDtoResponse.getCategory())
                .stock(productDtoResponse.getStock())
                .build();
        productRepository.delete(product);
        return true;
    }

    private ProductDtoResponse convertToDto(Product product) {
        return ProductDtoResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .stock(product.getStock())
                .build();
    }
}
