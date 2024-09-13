package com.example.deliverymanager.service.impl;

import com.example.deliverymanager.client.CommandClient;
import com.example.deliverymanager.dto.DeliveryDtoRequest;
import com.example.deliverymanager.dto.DeliveryDtoResponse;
import com.example.deliverymanager.dto.commandDto.CommandDto;
import com.example.deliverymanager.model.Delivery;
import com.example.deliverymanager.repository.DeliveryRepository;
import com.example.deliverymanager.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private CommandClient commandClient;

    @Override
    public DeliveryDtoResponse createDelivery(DeliveryDtoRequest deliveryDtoRequest) {

        CommandDto commandDto = commandClient.getCommandById(deliveryDtoRequest.getCommandId());

        if (commandDto == null) {
            throw new RuntimeException("Command not found");
        }

        Delivery delivery = Delivery.builder()
                .expectedDeliveryDate(LocalDate.parse(deliveryDtoRequest.getExpectedDeliveryDate()))
                .actualDeliveryDate(LocalDate.parse(deliveryDtoRequest.getActualDeliveryDate()))
                .deliveryStatus(deliveryDtoRequest.getDeliveryStatus())
                .deliveryAddress(deliveryDtoRequest.getDeliveryAddress())
                .commandId(deliveryDtoRequest.getCommandId())
                .build();
        deliveryRepository.save(delivery);
        return convertToDtoResponse(delivery);
    }

    @Override
    public DeliveryDtoResponse getDeliveryById(int id) {

        return deliveryRepository.findById(id)
                .map(this::convertToDtoResponse)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    @Override
    public List<DeliveryDtoResponse> getAllDeliveries() {

        return deliveryRepository.findAll().stream()
                .map(this::convertToDtoResponse)
                .toList();
    }

    @Override
    public DeliveryDtoResponse updateDelivery(int id, DeliveryDtoRequest deliveryDtoRequest) {

        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setExpectedDeliveryDate(LocalDate.parse(deliveryDtoRequest.getExpectedDeliveryDate()));
        delivery.setActualDeliveryDate(LocalDate.parse(deliveryDtoRequest.getActualDeliveryDate()));
        delivery.setDeliveryStatus(deliveryDtoRequest.getDeliveryStatus());
        delivery.setDeliveryAddress(deliveryDtoRequest.getDeliveryAddress());
        delivery.setCommandId(deliveryDtoRequest.getCommandId());
        deliveryRepository.save(delivery);
        return convertToDtoResponse(delivery);
    }

    @Override
    public boolean deleteDelivery(int id) {

        if (deliveryRepository.existsById(id)) {
            deliveryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DeliveryDtoResponse convertToDtoResponse(Delivery delivery) {
        return DeliveryDtoResponse.builder()
                .id(delivery.getId())
                .expectedDeliveryDate(delivery.getExpectedDeliveryDate())
                .actualDeliveryDate(delivery.getActualDeliveryDate())
                .deliveryStatus(delivery.getDeliveryStatus())
                .deliveryAddress(delivery.getDeliveryAddress())
                .commandDto(commandClient.getCommandById(delivery.getCommandId()))
                .build();
    }
}
