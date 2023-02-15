package com.shopping.app.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.app.model.OrderItem;
import com.shopping.app.dao.jdbc.OrderItemDao;
import com.shopping.app.model.Order;
import com.shopping.app.dao.jdbc.OrderDao;
import com.shopping.app.exceptions.Exceptions.DbException;

public class OrderItemDaoImpl implements OrderItemDao {
    private Connection conn;

    public OrderItemDaoImpl(Connection con) {
	this.conn = con;
    }

    @Override
    public void insert(OrderItem orderItem) {
	final String sql = "insert into order_item(id_order, id_product, units, total_price) values(?,?,?)";
	try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    // Set the parameters for the insert.

	    pstmt.setInt(1, orderItem.getOrder().getId());
	    pstmt.setInt(2, orderItem.getProduct().getId());
	    pstmt.setInt(3, orderItem.getUnits());
	    pstmt.setDouble(4, orderItem.getTotal());
	   

	    pstmt.executeUpdate();

	    // Get the generated primary key.
	    try (ResultSet rs = pstmt.getGeneratedKeys()) {
		if (rs.next()) {
		    int id = rs.getInt(1);
		    orderItem.setId(id);
		}
	    } catch (SQLException ex) {
			throw new DbException(ex);
	    }
		} catch (SQLException ex) {
	    	throw new DbException(ex);
		}
    }

 /** 
    @Override
    public Product findById(int idProduct) {

	final String sql = "select id_product, description, price, barcode from product where id_product = ?";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Set the parameters for the query.
	    ps.setInt(1, idProduct);

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Product product = new Product();
		    product.setId(rs.getInt(1));
		    product.setDescription(rs.getString(2));
		    product.setPrice(rs.getDouble(3));
		    product.setBarcode(rs.getString(4));

		    return product;
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
	return null;
    }

 
    @Override
    public List<Product> findAll() {
	List<Product> products = new ArrayList<>();
	
	final String sql = "select id_product, description, price, barcode from product";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		   Product product = new Product();
		    product.setId(rs.getInt(1));
		    product.setDescription(rs.getString(2));
		    product.setPrice(rs.getDouble(3));
		    product.setBarcode(rs.getString(4));

		    products.add(product);
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
	return products;
    }


    @Override
    public void update(Product product) {
    	
	final String sql = "update product set name= ?, description= ?, price= ?, barcode= ? where id_product = ?";
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    // Set the parameters for the update.
	    pstmt.setString(1, product.getName());
	    pstmt.setString(2, product.getDescription());
	    pstmt.setDouble(3, product.getPrice());
	    pstmt.setString(4, product.getBarcode());

	    pstmt.setInt(10, product.getId());

	    pstmt.executeUpdate();
		} catch (SQLException ex) {
	    throw new DbException(ex);
		}
	
    }

    @Override
    public void delete(Product product) {
		final String sql = "delete from product where id_product = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	// Set the parameters for the delete.
	   	 pstmt.setInt(1, product.getId());

	   	 pstmt.executeUpdate();
		} catch (SQLException ex) {
	    throw new DbException(ex);
		}
    }
    */
}
