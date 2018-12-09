package database;

import java.util.ArrayList;

import javax.ejb.Local;

import beans.Order;

@Local
public interface DataAccessInterface {
	// All CRUD Operations
	
	// CREATE
	public int addOrder(Order order);
	// READ
	public ArrayList<Order> getOrders();
	public Order readOne(int id);
	// UPDATE
	public int updateOrder(int id, Order order);
	// DELETE
	public int deleteOrder(int id);
}
