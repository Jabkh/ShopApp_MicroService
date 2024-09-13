package com.example.commandmanager.dto.commandLine;

import com.example.commandmanager.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class CommandLineDtoResponse {
    private int id;
    private double quantity;
    private double subTotal;
    private ProductDto product;
    private int commandId;
}
