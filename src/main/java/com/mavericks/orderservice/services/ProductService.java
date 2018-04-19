package com.mavericks.orderservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericks.orderservice.http.RestClient;
import com.mavericks.orderservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
class ProductService {

  @Value("${product-service.host}")
  private String server;

  @Value("${product-service.port}")
  private String port;

  @Autowired
  private RestClient restClient;

  @Autowired
  private ObjectMapper objectMapper;

  List<Product> getProducts(String productIds) throws IOException {
    ResponseEntity<String> responseEntity = restClient.get(server, port, "/products?ids=" + productIds);
    return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<Product>>(){});
  }
}
