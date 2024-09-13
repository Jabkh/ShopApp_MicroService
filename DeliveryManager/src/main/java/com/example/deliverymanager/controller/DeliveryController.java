package com.example.deliverymanager.controller;

import com.example.deliverymanager.dto.DeliveryDtoRequest;
import com.example.deliverymanager.dto.DeliveryDtoResponse;
import com.example.deliverymanager.service.impl.DeliveryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryServiceImpl deliveryService;

    @GetMapping
    public ResponseEntity<List<DeliveryDtoResponse>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    @PostMapping("/create")
    public ResponseEntity<DeliveryDtoResponse> createDelivery(@RequestBody DeliveryDtoRequest delivery) {
        return ResponseEntity.ok(deliveryService.createDelivery(delivery));
    }


}
