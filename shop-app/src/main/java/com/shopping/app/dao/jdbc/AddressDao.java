package com.shopping.app.dao.jdbc;

import com.shopping.app.model.Address;
import java.util.List;

/**
 * Provides CRUD operations for the address table.
 *
 * - Create
 * - Read
 * - Update
 * - Delete
 */
public interface AddressDao {
  public void insert(Address address);

  public Address findById(int idAddress);

  public List<Address> findAll();

  public void update(Address address);

  public void delete(Address address);
}