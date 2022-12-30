package com.shopping.app.model;

public class OrderItem {
  private int id;
  private int units;
  private Product product;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUnits() {
    return units;
  }

  public void setUnits(int units) {
    this.units = units;
  }

  public double getTotal() {
    double total = units * product.getPrice();
    return total;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
