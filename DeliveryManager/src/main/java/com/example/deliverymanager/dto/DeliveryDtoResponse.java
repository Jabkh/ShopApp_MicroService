package com.example.deliverymanager.dto;

import com.example.deliverymanager.dto.commandDto.CommandDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder

public class DeliveryDtoResponse {
    private int id;
    private LocalDate expectedDeliveryDate;
    private LocalDate actualDeliveryDate;
    private String deliveryStatus;
    private String deliveryAddress;
    private CommandDto commandDto;
}
