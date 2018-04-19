package com.mavericks.orderservice.models;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String productIds;

  @Column(nullable = false)
  private BigDecimal total;

  public Order() {

  }

  public Long getId() {
    return id;
  }

  public String getProductIds() {
    return productIds;
  }

  public BigDecimal getTotal() { return total; }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProductIds(String productIds) {
    this.productIds = productIds;
  }

  public void setTotal(BigDecimal total) { this.total = total; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(id, order.id) &&
        Objects.equals(productIds, order.productIds) &&
        Objects.equals(total, order.total);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, productIds, total);
  }
}
