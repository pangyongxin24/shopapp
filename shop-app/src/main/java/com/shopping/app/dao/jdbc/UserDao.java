package com.shopping.app.dao.jdbc;

import com.shopping.app.model.User;
import java.util.List;

/**
 * Provides CRUD operations for the user table.
 *
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface UserDao {
    public void insert(User user);

    public User findById(int idUser);

    public List<User> findAll();

    public void update(User user);

    public void delete(User user);

}