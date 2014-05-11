package com.yummynoodlebar.persistence.services;

import java.util.ArrayList;
import java.util.List;

import com.yummynoodlebar.events.orders.SetOrderPaymentEvent;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.OrderStatus;
import com.yummynoodlebar.persistence.repository.OrdersRepository;

public class OrderPersistenceEventHandler implements OrderPersistenceService {

	private final OrdersRepository orderRepository;

	public OrderPersistenceEventHandler(final OrdersRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public void createOrder(Order order) {
		order = orderRepository.save(order);
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
	public Order requestOrderDetails(String key) {

		Order order = orderRepository.findOne(key);

		return order;
	}

	@Override
	public void setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
		Order order = orderRepository.findOne(setOrderPaymentEvent.getKey()
				.toString());
		// TODO, handling payment details...
	}

	@Override
	public void deleteOrder(String key) {
		Order order = orderRepository.findOne(key);
		orderRepository.delete(key.toString());
	}

	@Override
	public void setOrderStatus(OrderStatus status) {
		// TODO Auto-generated method stub

	}

}
