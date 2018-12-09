package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import beans.Order;

@Local
public interface OrdersBusinessInterface {
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
	public void sendOrder(Order order);
}

