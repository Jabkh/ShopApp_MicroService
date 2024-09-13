package com.example.deliverymanager.client;

import com.example.deliverymanager.dto.commandDto.CommandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommandClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${command.service.url}")
    private String commandServiceUrl;

    public CommandDto getCommandById(int commandId) {
        return restTemplate.getForObject(commandServiceUrl + commandId, CommandDto.class);
    }
}

