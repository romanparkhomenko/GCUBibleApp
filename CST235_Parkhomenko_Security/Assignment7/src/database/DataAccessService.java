package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;

import beans.Order;


@Stateless
@Local(DataAccessInterface.class)
public class DataAccessService implements DataAccessInterface {
	
	// Credentials to the postgresql server.
	// Database called ordermanagement. UN: postgres. PW: none. 
	// May change these creds to work with your DB config,
	// but please don't push those changes to GitHub.
	// Best to create schema and table by running SQL scripts found under resources.
	String dbURL = "jdbc:postgresql://localhost:5432/test";
	String user = "postgres";
	String password = "";
	Connection conn = null;
	
	// Declare and initialize rows affected variable.
	int numberOfRowsAffected = 0;
	int rowsAffected = 0;
	
	// addProduct function to create product in DB.
	@Override
	public int addOrder(Order order) {
		PreparedStatement statement = null;
		try {
			//Establish connection to DB and create new Product in Database
			conn = DriverManager.getConnection(dbURL, user, password);
//			System.out.println("Connection to database successful!");
			//SQL Query Statement
			statement = conn.prepareStatement("INSERT INTO testapp.ORDERS (ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) values (?, ?, ?, ?)");
			statement.setString(1, order.getOrderNo());
			statement.setString(2, order.getProductName());
			statement.setFloat(3, order.getPrice());
			statement.setInt(4, order.getQuantity());
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		} finally {
			// Close query and connection.
			try {
				statement.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return numberOfRowsAffected;
	}
	
	// readAll function that returns all products in DB.
	@Override
	public ArrayList<Order> getOrders() {
		
		//Create new productList array.
		ArrayList<Order> ordersList = new ArrayList<>();
		Order order;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			//Establish connection to DB and create new Product in Database
			conn = DriverManager.getConnection(dbURL, user, password);
//			System.out.println("Connection to database successful!");
			//SQL Query Statement
			statement = conn.createStatement();
			rs = statement.executeQuery("SELECT * FROM testapp.Orders");
			
			//Loop through DB table and add items into list.
			while(rs.next()){
				//Commented out to prevent crazy console logs
//				int id = rs.getInt(1);
//		    	String productName = rs.getString(2);
//		    	int quantity = rs.getInt(3);
//		    	double cost = rs.getFloat(4);
//		    	String description = rs.getString(5);
//		        System.out.println("ID: " + id + " Product Name: " + productName + " Quantity: " + quantity + " Cost: " + cost + " Description: " + description);
				
				order = new Order(rs.getString(2),rs.getString(3) , rs.getFloat(4), rs.getInt(5));
				ordersList.add(order);
			}
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		}finally {
			//Close resultset, query, and connection.
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return ordersList;
	}
	
	// readOne function to read single item from DB based on given index.
	@Override
	public Order readOne(int id){
		// Declare product and statement variables.
		Order order = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			//Establish connection to DB and create new Product in Database
			conn = DriverManager.getConnection(dbURL, user, password);
//			System.out.println("Connection to database successful!");
			//SQL Query Statement
			statement = conn.prepareStatement("SELECT * FROM testapp.Orders WHERE id=?");
			statement.setInt(1, id);
			rs = statement.executeQuery();
			while(rs.next()){
				order = new Order(rs.getString(2),rs.getString(3) , rs.getFloat(4), rs.getInt(5));
			}
		} catch (SQLException e){
			System.out.println("Error connecting to database");
			e.printStackTrace();
		} finally {
			//Close resultset, query, and connection.
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return order;
	}
	
	// updateProduct Function to update product details
	@Override
	public int updateOrder(int id, Order order) {
		PreparedStatement statement = null;
		try {
			//Establish connection to DB and create new Product in Database
			conn = DriverManager.getConnection(dbURL, user, password);
//			System.out.println("Connection to database successful!");
			//SQL Query Statement
			statement = conn.prepareStatement("UPDATE testapp.Orders SET ORDER_NO = ?, PRODUCT_NAME = ?, PRICE =?, QUANTITY =? WHERE id = ? ");
			statement.setString(1, order.getOrderNo());
			statement.setString(2, order.getProductName());
			statement.setFloat(3, order.getPrice());
			statement.setInt(4, order.getQuantity());
			statement.setInt(5, id);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		} finally {
			//Close query and DB connection
			try {
				statement.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return numberOfRowsAffected;
	}
	
	public int deleteOrder(int id) {
		PreparedStatement statement = null;
		try {
			//Establish connection to DB and create new Product in Database
			conn = DriverManager.getConnection(dbURL, user, password);
//			System.out.println("Connection to database successful!");
			//SQL Query Statement
			statement = conn.prepareStatement("DELETE FROM testapp.Orders WHERE id = ?");
			statement.setInt(1, id);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		} finally {
			//Close query and DB connection
			try {
				statement.close();
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return numberOfRowsAffected;
	}

}