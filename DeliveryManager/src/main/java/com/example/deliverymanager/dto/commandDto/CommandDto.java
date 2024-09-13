package com.example.deliverymanager.dto.commandDto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder

public class CommandDto {
    private int id;
    private String status;
    private LocalDate date;
    private int customerId;

}
