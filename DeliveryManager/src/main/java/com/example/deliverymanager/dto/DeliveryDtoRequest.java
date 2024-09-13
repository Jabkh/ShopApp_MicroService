package com.example.deliverymanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class DeliveryDtoRequest {
    private String expectedDeliveryDate;
    private String actualDeliveryDate;
    private String deliveryStatus;
    private String deliveryAddress;
    private int commandId;

}
