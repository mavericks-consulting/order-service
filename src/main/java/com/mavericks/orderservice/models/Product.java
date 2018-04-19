package com.mavericks.orderservice.models;

import java.math.BigDecimal;

public class Product {
  private String id;
  private String name;
  private BigDecimal price;

  public Product() {
  }

  public Product(String id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
