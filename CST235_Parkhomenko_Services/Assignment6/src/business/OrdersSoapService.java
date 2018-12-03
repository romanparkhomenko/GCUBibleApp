package business;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import beans.Order;

@WebService()
public class OrdersSoapService {
	
	@Inject
	OrdersBusinessInterface service;
	
	@WebMethod()
	public String sayHello(String name) {
	    System.out.println("Hello: " + name);
	    return "Hello " + name + "!";
	}
	
	@WebMethod()
	public List<Order> getOrders() {
		return service.getOrders();
	}
}
