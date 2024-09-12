package com.example.commandmanager.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder

public class CommandDtoResponse {
    private int id;
    private String status;
    private LocalDate date;
    private int customerId;
}
