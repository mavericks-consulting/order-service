package com.mavericks.orderservice.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mavericks.orderservice.models.Order;
import com.mavericks.orderservice.services.OrdersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import utils.TestUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = OrdersController.class)
@AutoConfigureMockMvc
public class OrdersControllerRoutingTest {

  private MockMvc mockMvc;

  @Mock
  private OrdersService ordersService;

  @InjectMocks
  private OrdersController ordersController;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(ordersController)
        .build();
  }

  @Test
  public void shouldCreateOrder() throws Exception {
    String productIds = "a,b";
    Order createdOrder = new Order();
    createdOrder.setId(1L);
    createdOrder.setProductIds(productIds);

    Order orderToCreate = new Order();
    orderToCreate.setProductIds(productIds);

    when(ordersService.create(any(Order.class))).thenReturn(createdOrder);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/orders")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
            .content(TestUtil.asJsonString(orderToCreate)))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("{\"id\":1,\"productIds\":\"a,b\"}")));
  }

  @Test
  public void shouldFetchOrderById() throws Exception {
    Long orderId = 1L;

    Order expectedOrder = new Order();
    expectedOrder.setId(orderId);
    expectedOrder.setProductIds("a,b");

    when(ordersService.getById(orderId)).thenReturn(expectedOrder);

    mockMvc.perform(
        MockMvcRequestBuilders
            .get("/orders/1")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("{\"id\":1,\"productIds\":\"a,b\"}")));
  }

  @Test
  public void shouldThrowNotFoundIfOrderDoesNotExist() throws Exception {
    Long orderId = 20L;

    when(ordersService.getById(orderId)).thenThrow(new ResourceNotFoundException());

    mockMvc.perform(
        MockMvcRequestBuilders
            .get("/orders/20")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isNotFound());
  }
}