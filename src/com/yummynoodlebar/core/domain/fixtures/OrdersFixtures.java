package com.yummynoodlebar.core.domain.fixtures;

import java.util.Map;
import java.util.UUID;

import com.yummynoodlebar.persistence.domain.Customer;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.Product;

public class OrdersFixtures {

	public static final String YUMMY_ITEM = "yummy_core";

	public static Order standardOrder() {
		String key = UUID.randomUUID().toString();
		Customer customer = new Customer();
		Map<Product, Integer> orderItems = null;
		Order order = new Order(customer, orderItems, key);
		return order;
	}

}
