package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.User;
import business.OrdersBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController {
	
	@Inject
	OrdersBusinessInterface services;
	
	public String onSubmit() throws SQLException{

		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Use User object in post request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		getAllOrders();
		insertOrder();
		getAllOrders();
		
		// Forward to next page
		return "TestResponse.xhtml";
	}
	
	public OrdersBusinessInterface getService() {
		return services;
	}
	
	private void getAllOrders() throws SQLException {
		services.getOrders();
	}
	
	private void insertOrder() throws SQLException {
		for (int i = 0; i < 4; i++) {
			Order order = new Order("New Order # " + i, "New Product", (float) (i * 1.25), i * 4);
			services.addOrder(order);
		}
	}
	
	public String onSendOrder() {
		FacesContext context = FacesContext.getCurrentInstance();
		Order testOrder = context.getApplication().evaluateExpressionGet(context, "#{order}", Order.class);
		
		testOrder.setOrderNo("Test #1");
		testOrder.setProductName("This is a test order");
		testOrder.setPrice((float) 1.50);
		testOrder.setQuantity(30);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("order", testOrder);
		services.sendOrder(testOrder);
//		System.out.println("button clicked" + testOrder);
		return "OrderResponse.xhtml";
	}

}
