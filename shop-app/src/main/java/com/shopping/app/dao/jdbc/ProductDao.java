package com.shopping.app.dao.jdbc;

import com.shopping.app.model.Product;
import java.util.List;

/**
 * Provides CRUD operations for the product table.
 *
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface ProductDao {
    public void insert(Product product);

    public Product findById(int idProduct);

    public List<Product> findAll();

    public void update(Product product);

    public void delete(Product product);
}