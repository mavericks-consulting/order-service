package com.mavericks.orderservice.services;

import com.mavericks.orderservice.models.Order;
import com.mavericks.orderservice.models.Product;
import com.mavericks.orderservice.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;


@Service
public class OrdersService {
  @Autowired
  private ProductService productService;
  @Autowired
  private OrdersRepository ordersRepository;

  public Order create(Order order) throws IOException {
    BigDecimal orderTotal =
        productService.getProducts(order.getProductIds())
            .stream()
            .map(Product::getPrice)
            .reduce(
                BigDecimal.ZERO,
                (tempTotal, nextPrice) -> nextPrice.add(tempTotal));

    order.setTotal(orderTotal);

    return ordersRepository.save(order);
  }

  public Order getById(Long orderId) {
    Order byId = ordersRepository.findById(orderId);

    if (byId == null) throw new ResourceNotFoundException("Order with orderId "+ orderId + " does not exist");

    return byId;
  }
}
