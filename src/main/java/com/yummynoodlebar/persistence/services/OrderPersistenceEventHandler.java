package com.yummynoodlebar.persistence.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.yummynoodlebar.events.orders.SetOrderPaymentEvent;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.OrderStatus;
import com.yummynoodlebar.persistence.repository.OrderStatusRepository;
import com.yummynoodlebar.persistence.repository.OrdersRepository;

public class OrderPersistenceEventHandler implements OrderPersistenceService {

	private final OrdersRepository orderRepository;
	private final OrderStatusRepository orderStatusRepository;

	public OrderPersistenceEventHandler(final OrdersRepository orderRepository,
			final OrderStatusRepository orderStatusRepository) {
		this.orderRepository = orderRepository;
		this.orderStatusRepository = orderStatusRepository;
	}

	@Override
	public void setOrderStatus(OrderStatus status) {

		status = orderStatusRepository.save(status);

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
	public Order requestOrderDetails(UUID key) {

		Order order = orderRepository.findOne(key.toString());

		return order;
	}

	@Override
	public void setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent) {
		Order order = orderRepository.findOne(setOrderPaymentEvent.getKey()
				.toString());
		// TODO, handling payment details...

	}

	@Override
	public void deleteOrder(UUID key) {
		Order order = orderRepository.findOne(key.toString());
		orderRepository.delete(key.toString());
	}

	@Override
	public OrderStatus requestOrderStatus(UUID key) {
		OrderStatus status = orderStatusRepository.findLatestById(key);

		if (status == null) {
			return null;
		}

		return status;
	}
}
