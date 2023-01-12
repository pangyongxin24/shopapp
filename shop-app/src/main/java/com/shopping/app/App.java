package com.shopping.app;

import com.shopping.app.model.Address;
import com.shopping.app.model.Order;
import com.shopping.app.model.OrderItem;
import com.shopping.app.model.User;
import com.shopping.app.model.Product;
import com.shopping.app.dao.jdbc.impl.UserDaoImpl;
import com.shopping.app.dao.jdbc.UserDao;
import com.shopping.app.dao.jdbc.impl.ProductDaoImpl;
import com.shopping.app.dao.jdbc.ProductDao;

import com.shopping.app.dao.jdbc.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Hello world!
 * In order to compile the application, we need to tell where to find
 * main class
 * 
 * The commands below have to be executed in the root directory of the
 * project, where the pom.xml is located.
 * 
 * mvn package
 * java -cp target/shop-app-1.0-SNAPSHOT.jar com.shopping.app.App
 * 
 * link to create the maven project from sc
 * 
 * https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
 * 
  */
public class App {

  /**
   * A method that receives an address and print an
   * address
   */
  public static void printAddress(Address address) {
    System.out.println("Line1: " + address.getLine1());
    System.out.println("Line2: " + address.getLine2());
    System.out.println("City: " + address.getCity());
    System.out.println("Estate: " + address.getEstate());
    System.out.println("Country: " + address.getCountry());
    System.out.println("Zipcode: " + address.getZipCode());
  }

  /**
    A method that sets the address info and return it
  */
    public static Address addressSetter(){
      Address address = new Address();
      address.setLine1("Shui Dong Street");
      address.setLine2("Spring Road");
      address.setCity("QinZhou");
      address.setEstate("GuangXi");
      address.setCountry("China");
      address.setZipCode("94303");
      return address;
    }

  /**
    A method that sets the user info and return it
  */
  public static User userSetter() {
    User user = new User();
    user.setName("Serena");
    user.setLastName("Pan");
    user.setAge(18);
    user.setPhoneNumber("425 964 7078");
    Address address = new Address();
    address.setLine1("Shui Dong Street");
    address.setLine2("Spring Road");
    address.setCity("QinZhou");
    address.setEstate("GuangXi");
    address.setCountry("China");
    address.setZipCode("94303");
    user.setAddress(address);
    return user;
  }

  /**
    A method that receives an user and print it
  */
  public static void printUser(User user) {
    System.out.println("Name: " + user.getName());
    System.out.println("Last Name: " + user.getLastName());
    System.out.println("Age: " + user.getAge());
    System.out.println("Phone Number: " + user.getPhoneNumber());
    System.out.println("Address: " + user.getAddress().getLine1());
  }

    /**
    A method that sets the product info and return it
  */
  public static Product productSetter() {
    Product product = new Product();
    product.setName("Eyeshadow");
    product.setDescription("An eyeshadow that contains 9 different colors");
    product.setPrice(9.9);
    product.setBarcode("fgryelafu");
    return product;
  }

  /**
    A method that receives an product and print it
  */
  public static void printProduct(Product product) {
    System.out.println("Name: " + product.getName());
    System.out.println("Description: " + product.getDescription());
    System.out.println("Price: " + product.getPrice());
    System.out.println("Barcode: " + product.getBarcode());
  }

     /**
    A method that sets the orderItem info and return it
  */
  public static OrderItem orderItemSetter() {
    OrderItem orderItem = new OrderItem();
    orderItem.setUnits(4);
     Product product = new Product();
    product.setName("Eyeshadow");
    product.setDescription("An eyeshadow that contains 9 different colors");
    product.setPrice(9.9);
    product.setBarcode("fgryelafu");
    orderItem.setProduct(product);
    return orderItem;
  }

  /**
    A method that receives an orderItem and print it
  */
  public static void printOrderItem(OrderItem orderItem) {
    System.out.println("Units: " + orderItem.getUnits());
    Product product = orderItem.getProduct();
    System.out.println("Product: " + product.getName());
    System.out.println("Total price: " +orderItem.getTotal());
  }
  
  /**
    A method that sets the order info and return it
  */
  public static Order orderSetter() {
    Order order = new Order();
   // Date date = new Date(2022-12-11 20:03:22);
   // order.setCreated(date);
    User user = new User();
    user.setName("Serena");
    user.setLastName("Pan");
    user.setAge(18);
    user.setPhoneNumber("425 964 7078");
    Address address = new Address();
    address.setLine1("Shui Dong Street");
    address.setLine2("Spring Road");
    address.setCity("QinZhou");
    address.setEstate("GuangXi");
    address.setCountry("China");
    address.setZipCode("94303");
    user.setAddress(address);
    order.setUser(user);

    OrderItem orderItem = new OrderItem();
    orderItem.setUnits(4);
    Product product = new Product();
    product.setName("Eyeshadow");
    product.setDescription("An eyeshadow that contains 9 different colors");
    product.setPrice(9.9);
    product.setBarcode("fgryelafu");
    orderItem.setProduct(product);
    
    List<OrderItem> items = new ArrayList<OrderItem>();
    items.add(orderItem);
    return order;
  }

  /**
    A method that receives an order and print it
  */
  public static void printOrder(Order order) {
    System.out.println("User: " + order.getUser().getName());
    System.out.println("Date: " + order.getCreated());
    List<OrderItem> items = order.getItems();
    for(int i = 0; i < items.size(); i++) {
      System.out.println("item " + i + ": "  + items.get(i));
    }  
  }
 
  public static void main(String[] args) {
    System.out.println("Welcome to my app!");
    
    Address address = addressSetter();
    printAddress(address);

    User user = userSetter();
    printUser(user);

    Product product = productSetter();
    printProduct(product);

    OrderItem orderItem = orderItemSetter();
    printOrderItem(orderItem);

    Order order = orderSetter();
    printOrder(order);

//testing user dao impl

    UserDao userDaoImpl = new UserDaoImpl(DbConnection.getConnection());
    userDaoImpl.insert(user);

    System.out.println("Test Find by ID: ");
    User user1 = userDaoImpl.findById(1);
    System.out.println( " Name: " + user1.getName());


    System.out.println("Test update: ");
    User user3 = new User();
    user3.setName("Hey");
    user3.setLastName("Pan");
    user3.setAge(19);
    user3.setPhoneNumber("425 994 7033");
    Address address3 = new Address();
    address3.setLine1("Shui Dong Street");
    address3.setLine2("Spring Road");
    address3.setCity("QinZhou");
    address3.setEstate("GuangXi");
    address3.setCountry("China");
    address3.setZipCode("94313");
    user3.setAddress(address3);

    userDaoImpl.update(user);


    System.out.println("Test Find all: ");
    List<User> listOfUsers = userDaoImpl.findAll();

    for (int i = 0; i < listOfUsers.size(); i++) {
        System.out.println("user " + i + " :" +listOfUsers.get(i).getName());
    }


    System.out.println("Test delete: ");
    userDaoImpl.delete(user);
//finish testing all functionalities in userdao impl

    ProductDao productDaoImpl = new ProductDaoImpl(DbConnection.getConnection());
    productDaoImpl.insert(product);


  }
}
