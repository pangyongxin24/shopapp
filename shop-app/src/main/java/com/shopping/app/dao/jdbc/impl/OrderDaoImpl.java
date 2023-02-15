package com.shopping.app.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import com.shopping.app.model.Order;
import com.shopping.app.dao.jdbc.OrderDao;
import com.shopping.app.model.User;
import com.shopping.app.dao.jdbc.UserDao;

import com.shopping.app.exceptions.Exceptions.DbException;

public class OrderDaoImpl implements OrderDao {
    private Connection conn;

    public OrderDaoImpl(Connection con) {
	this.conn = con;
    }

    @Override
    public void insert(Order order) {
	final String sql = "insert into orders(id_user, total_price, created) values(?,?,?)";
	try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    // Set the parameters for the insert.
	    pstmt.setInt(1, order.getUser().getId());
	    pstmt.setDouble(2, order.getTotal());
	    pstmt.setDate(3, new java.sql.Date(order.getCreated().getTime()));

	    pstmt.executeUpdate();

	    // Get the generated primary key.
	    try (ResultSet rs = pstmt.getGeneratedKeys()) { 
		if (rs.next()) {
		    int id = rs.getInt(1);
		    order.setId(id);
		}
	    } catch (SQLException ex) {
			throw new DbException(ex);
	    }
		} catch (SQLException ex) {
	    	throw new DbException(ex);
		}
    }

    @Override
    public Order findById(int idOrder) {

	final String sql = "select id_order, created from orders where id_order = ?";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Set the parameters for the query.
	    ps.setInt(1, idOrder);

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Order order = new Order();
			order.setId(rs.getInt(1));
		    order.setCreated(rs.getTime(2));
		   
		    return order;
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
    public List<Order> findAll() {
	List<Order> orders = new ArrayList<>();
	
	final String sql = "select id_order, created from orders";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    Order order = new Order();
		    order.setId(rs.getInt(1));
		    order.setCreated(rs.getTime(2));
		  
		    orders.add(order);
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
	return orders;
    }


    @Override
    public void delete(Order order) {
		final String sql = "delete from orders where id_order = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	// Set the parameters for the delete.
	   	 pstmt.setInt(1, order.getId());

	   	 pstmt.executeUpdate();
		} catch (SQLException ex) {
	    throw new DbException(ex);
		}
    }
}
