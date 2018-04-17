package com.mavericks.orderservice.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
  public String createOrder(List<String> productIds) {
    return "someId";
  }
}
