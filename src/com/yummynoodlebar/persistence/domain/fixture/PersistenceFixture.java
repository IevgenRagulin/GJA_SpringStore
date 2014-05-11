package com.yummynoodlebar.persistence.domain.fixture;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yummynoodlebar.core.domain.fixtures.OrdersFixtures;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.Product;

public class PersistenceFixture {

	public static Order standardOrder() {

		Order order = OrdersFixtures.standardOrder();
		order.setDateTimeOfSubmission(new Date());

		Map<Product, Integer> items = new HashMap<Product, Integer>();

		Product product1 = new Product();
		product1.setProductName("bike1");
		Product product2 = new Product();
		product2.setProductName("bike2");
		Product product3 = new Product();
		product3.setProductName("bike3");
		items.put(product1, 15);
		items.put(product2, 12);
		items.put(product3, 7);

		order.setOrderItems(items);

		return order;
	}

	public static Order yummy16Order() {
		Order order = OrdersFixtures.standardOrder();
		order.setDateTimeOfSubmission(new Date());

		Map<Product, Integer> items = new HashMap<Product, Integer>();

		Product product16 = new Product();
		product16.setProductName("bike16");
		items.put(product16, 22);

		order.setOrderItems(items);

		return order;
	}

}
