package com.mavericks.orderservice.controllers;

import com.mavericks.orderservice.models.Order;
import com.mavericks.orderservice.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
class OrdersController {
  @Autowired
  private OrdersService ordersService;

  @RequestMapping(
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  Order create(@RequestBody Order order) {
    return ordersService.create(order);
  }

  @RequestMapping(
      path = "/{orderId}",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  Order getById(@PathVariable Long orderId) {
    return ordersService.getById(orderId);
  }
}
