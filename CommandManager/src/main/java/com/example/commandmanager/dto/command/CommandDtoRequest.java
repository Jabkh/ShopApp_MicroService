package com.example.commandmanager.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class CommandDtoRequest {
    private String status;
    private String date;
    private int customerId;
}
