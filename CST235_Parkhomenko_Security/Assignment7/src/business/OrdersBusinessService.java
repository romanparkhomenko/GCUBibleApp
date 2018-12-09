package business;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import database.DataAccessInterface;
import beans.Order;

@Stateless
@Local(OrdersBusinessInterface.class)
public class OrdersBusinessService implements OrdersBusinessInterface {
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;
	
	@Inject
	DataAccessInterface db;
	
	OrderMessageService service;

	@Override
	public int addOrder(Order order) {
		return db.addOrder(order);
	}
	
	@Override
	public ArrayList<Order> getOrders() {
		return db.getOrders();
	}
	
	@Override
	public Order readOne(int id){
		return db.readOne(id);
	}
	
	@Override
	public int updateOrder(int id, Order order) {
		return db.updateOrder(id, order);
	}
	
	@Override
	public int deleteOrder(int id) {
		return db.deleteOrder(id);
	}
	
	public void sendOrder(Order order) {
		try {
			Connection connection = connectionFactory.createConnection();
			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage message1 = session.createTextMessage();
			ObjectMessage message2 = session.createObjectMessage();
			message1.setText("This is test message");
			message2.setObject(getOrders());
			messageProducer.send(message1);
			messageProducer.send(message2);
			connection.close();
		} 
		catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}