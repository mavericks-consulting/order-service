package com.mavericks.orderservice.models;


import java.io.Serializable;
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

  public Order() {

  }

  public Long getId() {
    return id;
  }

  public String getProductIds() {
    return productIds;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProductIds(String productIds) {
    this.productIds = productIds;
  }
}
