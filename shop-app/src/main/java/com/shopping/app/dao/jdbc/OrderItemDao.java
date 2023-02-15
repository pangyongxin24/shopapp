package com.shopping.app.dao.jdbc;

import com.shopping.app.model.OrderItem;
import java.util.List;

/**
 * Provides CRUD operations for the OrderItem table.
 *
 */
public interface OrderItemDao {

    public void insert(OrderItem orderItem);
    /**
    public OrderItem findById(int idOrderItem);

    public List<OrderItem> findAll();

    public void update(OrderItem orderItem);

    public void delete(OrderItem orderItem);
    */
}