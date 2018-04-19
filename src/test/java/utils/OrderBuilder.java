package utils;

import com.mavericks.orderservice.models.Order;

import java.math.BigDecimal;

public class OrderBuilder {
  private Order order = new Order();

  public OrderBuilder withId(Long id) {
    this.order.setId(id);
    return this;
  }

  public OrderBuilder withProductIds(String productIds) {
    this.order.setProductIds(productIds);
    return this;
  }

  public OrderBuilder withTotal(BigDecimal total) {
    this.order.setTotal(total);
    return this;
  }

  public Order build() {
    return this.order;
  }
}
