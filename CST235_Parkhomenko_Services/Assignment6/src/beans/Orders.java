package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Orders {
	
	
	List<Order> orders = new ArrayList<Order>();
	
	public List<Order> getOrders() {return orders;}
	public void setOrders(List<Order> orders) {this.orders = orders; }
	
	public Orders() {
//		orders.add(new Order("Order #1", "Water", (float)1.00, 81));
//		orders.add(new Order("Order #2", "Coke", (float)2.00, 15));
//		orders.add(new Order("Order #3", "Mountain Dew", (float)2.50, 32));
//		orders.add(new Order("Order #4", "Pepsi", (float)3.00, 44));
//		orders.add(new Order("Order #5", "Iced Tea", (float)4.00, 66));
//		orders.add(new Order("Order #6", "Coffee", (float)1.50, 55));
	}
}
