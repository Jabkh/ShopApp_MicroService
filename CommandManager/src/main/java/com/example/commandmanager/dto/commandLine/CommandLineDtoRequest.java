package com.example.commandmanager.dto.commandLine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class CommandLineDtoRequest {
    private double quantity;
    private double subTotal;
    private int productId;
    private int commandId;
}
