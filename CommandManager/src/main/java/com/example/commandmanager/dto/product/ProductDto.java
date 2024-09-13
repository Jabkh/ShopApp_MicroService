package com.example.commandmanager.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stock;
}
