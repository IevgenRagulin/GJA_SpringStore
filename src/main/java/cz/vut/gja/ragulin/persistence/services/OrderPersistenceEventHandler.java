package cz.vut.gja.ragulin.persistence.services;

import java.util.ArrayList;
import java.util.List;

import cz.vut.gja.ragulin.persistence.domain.Order;
import cz.vut.gja.ragulin.persistence.repository.OrdersRepository;

public class OrderPersistenceEventHandler implements OrderPersistenceService {

	private final OrdersRepository orderRepository;

	public OrderPersistenceEventHandler(final OrdersRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> requestAllOrders() {
		List<Order> generatedDetails = new ArrayList<Order>();
		for (Order order : orderRepository.findAll()) {
			generatedDetails.add(order);
		}
		return generatedDetails;
	}

	@Override
	public Order requestOrderDetails(int key) {
		Order order = orderRepository.findById(key);
		return order;
	}

	@Override
	public void deleteOrder(int key) {
		Order order = orderRepository.findById(key);
		orderRepository.delete(order);
	}

	@Override
	public void setOrderStatus(int key, String status) {
		Order order = orderRepository.findById(key);
		order.setStatus(status);
		orderRepository.save(order);
	}

}
