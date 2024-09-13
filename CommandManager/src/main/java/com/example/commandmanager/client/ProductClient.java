package com.example.commandmanager.client;

import com.example.commandmanager.dto.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public ProductDto getProductById(int productId) {
        return restTemplate.getForObject(productServiceUrl + productId, ProductDto.class);
    }
}



//@Autowired
//private RestTemplate restTemplate;
//
//@Value("${command.service.url}")
//private String commandServiceUrl;
//
//public CommandDto getCommandById(int commandId) {
//    return restTemplate.getForObject(commandServiceUrl + commandId, CommandDto.class);
//}
//}