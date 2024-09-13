package com.example.deliverymanager.service;

import com.example.deliverymanager.dto.DeliveryDtoRequest;
import com.example.deliverymanager.dto.DeliveryDtoResponse;

import java.util.List;

public interface IDeliveryService {
    DeliveryDtoResponse createDelivery(DeliveryDtoRequest deliveryDtoRequest);
    DeliveryDtoResponse getDeliveryById(int id);
    List<DeliveryDtoResponse> getAllDeliveries();
    DeliveryDtoResponse updateDelivery(int id, DeliveryDtoRequest deliveryDtoRequest);
    boolean deleteDelivery(int id);
}
