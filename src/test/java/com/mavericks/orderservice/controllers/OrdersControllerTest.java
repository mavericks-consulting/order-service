package com.mavericks.orderservice.controllers;

import static org.mockito.Mockito.when;

import com.mavericks.orderservice.models.Order;
import com.mavericks.orderservice.services.OrdersService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {

  @InjectMocks
  private OrdersController ordersController;

  @Mock
  private OrdersService ordersService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCreateANewOrder() {
    String productIds = "productId1,productId2";
    Order orderToCreate = new Order();
    orderToCreate.setId(1L);
    orderToCreate.setProductIds(productIds);

    when(ordersService.create(orderToCreate)).thenReturn(orderToCreate);

    Order actualOrder = ordersController.create(orderToCreate);

    Assert.assertEquals(orderToCreate, actualOrder);
  }

  @Test
  public void shouldFetchOrderById() {
    Long orderId = 2L;
    Order expectedOrder = new Order();
    expectedOrder.setId(orderId);
    expectedOrder.setProductIds("productid1, productid2");

    when(ordersService.getById(orderId)).thenReturn(expectedOrder);

    Order actualOrder = ordersController.getById(orderId);

    Assert.assertEquals(expectedOrder, actualOrder);
  }
}
