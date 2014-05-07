package com.yummynoodlebar.persistence.services;

import java.util.List;
import java.util.UUID;

import com.yummynoodlebar.events.orders.SetOrderPaymentEvent;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.OrderStatus;

public interface OrderPersistenceService {

	public List<Order> requestAllOrders();

	public Order requestOrderDetails(UUID requestOrderDetailsEvent);

	public OrderStatus requestOrderStatus(UUID key);

	public void createOrder(Order event);

	public void setOrderStatus(OrderStatus status);

	public void setOrderPayment(SetOrderPaymentEvent setOrderPaymentEvent);

	public void deleteOrder(UUID key);

}
