package cz.vut.gja.ragulin.persistence.services;

import java.util.List;

import cz.vut.gja.ragulin.persistence.domain.Order;

public interface OrderPersistenceService {

	public List<Order> requestAllOrders();

	public Order requestOrderDetails(int key);

	public Order createOrder(Order event);

	public void deleteOrder(int key);

	void setOrderStatus(int key, String status);

}
