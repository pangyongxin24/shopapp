package com.shopping.app.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.app.model.User;
import com.shopping.app.dao.jdbc.UserDao;
import com.shopping.app.exceptions.Exceptions.DbException;
import com.shopping.app.model.Address;

public class UserDaoImpl implements UserDao {
    private Connection conn;

    public UserDaoImpl(Connection con) {
	this.conn = con;
    }

    @Override
    public void insert(User user) {
	final String sql = "insert into user(name, last_name, age, phone_number, address_line1, address_line2, city, country, zipcode) values(?,?,?,?,?,?,?,?,?)";
	try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    // Set the parameters for the insert.
	    pstmt.setString(1, user.getName());
	    pstmt.setString(2, user.getLastName());
	    pstmt.setInt(3, user.getAge());
	    pstmt.setString(4, user.getPhoneNumber());
	    Address address = user.getAddress();
	    pstmt.setString(5, address.getLine1());
	    pstmt.setString(6, address.getLine2());
	    pstmt.setString(7, address.getCity());
	    pstmt.setString(8, address.getCountry());
		pstmt.setString(9, address.getZipCode());


	    pstmt.executeUpdate();

	    // Get the generated primary key.
	    try (ResultSet rs = pstmt.getGeneratedKeys()) {
		if (rs.next()) {
		    int id = rs.getInt(1);
		    user.setId(id);
		}
	    } catch (SQLException ex) {
			throw new DbException(ex);
	    }
		} catch (SQLException ex) {
	    	throw new DbException(ex);
		}
    }

   
    @Override
    public User findById(int idUser) {
	//final String sql = "select name, last_name, age, phone_number, address_line1, address_line2, city, country, zipcode from user where id_user = ?";

	final String sql = "select id_user, name, last_name, age, phone_number, address_line1, address_line2, city, country, zipcode from user where id_user = ?";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Set the parameters for the query.
	    ps.setInt(1, idUser);

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    User user = new User();
		    user.setId(rs.getInt(1));
		    user.setName(rs.getString(2));
		    user.setLastName(rs.getString(3));
		    user.setAge(rs.getInt(4));
		    user.setPhoneNumber(rs.getString(5));

		    //Address address = user.getAddress();
		    Address address = new Address();;
		    address.setLine1(rs.getString(6));
		    address.setLine2(rs.getString(7));
		    address.setCity(rs.getString(8));
		    address.setCountry(rs.getString(9));
		    address.setZipCode(rs.getString(10));
		    user.setAddress(address);

		    return user;
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
    public List<User> findAll() {
	List<User> users = new ArrayList<>();
	
	final String sql = "select id_user, name, last_name, age, phone_number, address_line1, address_line2, city, country, zipcode from user";
	try (PreparedStatement ps = conn.prepareStatement(sql)) {

	    // Process the results
	    try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		    User user = new User();
		    user.setId(rs.getInt(1));
		    user.setName(rs.getString(2));
		    user.setLastName(rs.getString(3));
		    user.setAge(rs.getInt(4));
		    user.setPhoneNumber(rs.getString(5));
		
		    Address address = new Address();;
		    address.setLine1(rs.getString(6));
		    address.setLine2(rs.getString(7));
		    address.setCity(rs.getString(8));
		    address.setCountry(rs.getString(9));
		    address.setZipCode(rs.getString(10));
		    user.setAddress(address);	

		    users.add(user);
		}
	    } catch (SQLException ex) {
		throw new DbException(ex);
	    }
	} catch (SQLException ex) {
	    throw new DbException(ex);
	}
	return users;
    }


    @Override
    public void update(User user) {
    	
	final String sql = "update user set name= ?, last_name= ?, age= ?, phone_number= ?, address_line1= ?, address_line2= ?, city= ?, country= ?, zipcode= ? where id_user = ?";
	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    // Set the parameters for the update.
	    pstmt.setString(1, user.getName());
	    pstmt.setString(2, user.getLastName());
	    pstmt.setInt(3, user.getAge());
	    pstmt.setString(4, user.getPhoneNumber());

	    Address address = user.getAddress();
	    pstmt.setString(5, address.getLine1());
	    pstmt.setString(6, address.getLine2());
	    pstmt.setString(7, address.getCity());
	    pstmt.setString(8, address.getCountry());
		pstmt.setString(9, address.getZipCode());
	    pstmt.setInt(10, user.getId());

	    pstmt.executeUpdate();
		} catch (SQLException ex) {
	    throw new DbException(ex);
		}
	
    }

    @Override
    public void delete(User user) {
		final String sql = "delete from user where id_user = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	// Set the parameters for the delete.
	   	 pstmt.setInt(1, user.getId());

	   	 pstmt.executeUpdate();
		} catch (SQLException ex) {
	    throw new DbException(ex);
		}
    }
}
