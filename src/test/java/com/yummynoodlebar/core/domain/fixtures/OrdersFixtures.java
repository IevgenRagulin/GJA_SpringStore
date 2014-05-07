package com.yummynoodlebar.core.domain.fixtures;

import com.yummynoodlebar.persistence.domain.Order;

public class OrdersFixtures {

	public static final String YUMMY_ITEM = "yummy_core";

	public static Order standardOrder() {
		Order order = new Order();
		return order;
	}

}
