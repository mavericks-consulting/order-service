package com.mavericks.orderservice.services;


import static org.mockito.Mockito.when;

import com.mavericks.orderservice.models.Order;
import com.mavericks.orderservice.repositories.OrdersRepository;
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
public class OrdersServiceTest {

  @InjectMocks
  private OrdersService ordersService;

  @Mock
  private OrdersRepository ordersRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCreateNewOrder() {
    String productIds = "productId1,productId2";
    Order orderToCreate = new Order();
    orderToCreate.setId(1L);
    orderToCreate.setProductIds(productIds);

    when(ordersRepository.save(orderToCreate)).thenReturn(orderToCreate);

    Order actualOrder = ordersService.create(orderToCreate);

    Assert.assertEquals(orderToCreate, actualOrder);

  }
}