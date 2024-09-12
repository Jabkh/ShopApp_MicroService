package com.example.productmanager.repository;

import com.example.productmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName(@Param("name") String name);

    List<Product> findByCategory(@Param("category") String category);
}
