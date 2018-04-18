package com.mavericks.orderservice.services;

import com.mavericks.orderservice.models.Order;
import com.mavericks.orderservice.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrdersService {
  @Autowired
  private OrdersRepository ordersRepository;

  public Order create(Order order) {
    return ordersRepository.save(order);
  }
}
