package com.mavericks.orderservice.repositories;

import com.mavericks.orderservice.models.Order;
import org.springframework.data.repository.*;

public interface OrdersRepository extends Repository<Order, Long>{
  Order save(Order order);
}
