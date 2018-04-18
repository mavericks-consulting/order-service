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
import org.springframework.data.rest.webmvc.*;

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

  @Test
  public void shouldFetchOrderById() {
    Long orderId = 2L;
    Order expectedOrder = new Order();
    expectedOrder.setId(orderId);
    expectedOrder.setProductIds("productid1,productid2");

    when(ordersRepository.findById(orderId)).thenReturn(expectedOrder);

    Order actualOrder = ordersService.getById(orderId);

    Assert.assertEquals(expectedOrder, actualOrder);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void shouldThrowNotFoundExceptionIfOrderDoesNotExist() {
    Long orderId = 2L;

    when(ordersRepository.findById(orderId)).thenReturn(null);

    ordersService.getById(orderId);
  }
}