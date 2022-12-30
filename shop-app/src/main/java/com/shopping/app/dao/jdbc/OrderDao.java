package com.shopping.app.dao.jdbc;

import com.shopping.app.model.Order;
import java.util.List;

/**
 * Provides CRUD operations for the order table.
 *
 * - Create
 * - Read
 * - Update
 * - Delete
 */
public interface OrderDao {
  public void insert(Order order);

  public Order findById(int idOrder);

  public List<Order> findAll();

  public void update(Order order);

  public void delete(Order order);
}