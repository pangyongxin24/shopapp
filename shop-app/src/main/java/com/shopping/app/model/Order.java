package com.shopping.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
  private int id;
  private User user;
  private Date created;
  private List<OrderItem> items;

  public Order() {
    items = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public double getTotal() {
    double total = 0;

    for (OrderItem item : items) {
      total += item.getTotal();
    }

    return total;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }
}
