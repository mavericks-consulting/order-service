package com.mavericks.orderservice.services;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mavericks.orderservice.models.Order;
import com.mavericks.orderservice.models.Product;
import com.mavericks.orderservice.repositories.OrdersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import utils.OrderBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTest {

  @InjectMocks
  private OrdersService ordersService;

  @Mock
  private OrdersRepository ordersRepository;
  @Mock
  private ProductService productService;

  private OrderBuilder orderBuilder = new OrderBuilder();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCreateNewOrder() throws IOException {
    String productId1 = "productId1";
    String productId2 = "productId2";
    String productIds = productId1 + "," + productId2;

    Order orderToCreate = orderBuilder.withId(1L).withProductIds(productIds).build();
    Order createdOrder = orderBuilder.withId(1L).withProductIds(productIds).withTotal(BigDecimal.valueOf(30.00)).build();

    when(productService.getProducts(productIds))
        .thenReturn(Arrays.asList(
            new Product(productId1, "Product1", BigDecimal.valueOf(10.00)),
            new Product(productId2, "Product2", BigDecimal.valueOf(20.00))));
    when(ordersRepository.save(createdOrder)).thenReturn(createdOrder);

    Order actualOrder = ordersService.create(orderToCreate);

    verify(ordersRepository, times(1)).save(createdOrder);
    verify(productService, times(1)).getProducts(productIds);
    Assert.assertEquals(createdOrder, actualOrder);
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