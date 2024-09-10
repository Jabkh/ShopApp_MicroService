package com.example.productmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;
}
