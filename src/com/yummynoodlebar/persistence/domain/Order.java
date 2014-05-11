package com.yummynoodlebar.persistence.domain;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;

@Entity(name = "NOODLE_ORDERS")
public class Order {

	@Column(name = "SUBMISSION_DATETIME")
	private Date dateTimeOfSubmission;

	@ElementCollection(fetch = FetchType.EAGER, targetClass = java.lang.Integer.class)
	@JoinTable(name = "ORDER_ORDER_ITEMS", joinColumns = @JoinColumn(name = "ID"))
	@MapKeyColumn(name = "MENU_ID")
	@Column(name = "VALUE", nullable = false)
	private Map<Product, Integer> orderItems;

	@Column(nullable = false)
	private String orderStatus;

	@Column(nullable = false)
	private String paymentMethod;

	@Id
	@Column(name = "ORDER_ID", nullable = false)
	private String id;

	@ManyToOne
	private Customer customer;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getCustomerName() {
		return customer.getName();
	}

	public Order() {
		this.id = UUID.randomUUID().toString();
		this.dateTimeOfSubmission = new Date();
	}

	public Order(Customer customer, Map<Product, Integer> orderItems, String key) {
		this.orderStatus = calculateInitialOrderStatusBasedOnPaymentMethod(customer);
		this.customer = customer;
		this.orderItems = orderItems;
		this.dateTimeOfSubmission = new Date();
		this.id = key;
	}

	private String calculateInitialOrderStatusBasedOnPaymentMethod(
			Customer customer) {
		if ("Credit card".equals(customer.getPaymentMethod())) {
			return OrderStatus.AWAITING_PAYMENT;
		} else if ("In cash on receival".equals(customer.getPaymentMethod())) {
			return OrderStatus.BEING_DELIVERED;
		} else if ("Wire transfer".equals(customer.getPaymentMethod())) {
			return OrderStatus.AWAITING_PAYMENT;
		} else {
			return OrderStatus.AWAITING_PAYMENT;
		}
	}

	public Order(Customer customer, Map<Product, Integer> orderItems) {
		this(customer, orderItems, UUID.randomUUID().toString());
	}

	public void setDateTimeOfSubmission(Date dateTimeOfSubmission) {
		this.dateTimeOfSubmission = dateTimeOfSubmission;
	}

	public String getStatus() {
		return orderStatus;
	}

	public void setStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}

	public String getId() {
		return id;
	}

	public void setOrderItems(Map<Product, Integer> orderItems) {
		if (orderItems == null) {
			this.orderItems = Collections.emptyMap();
		} else {
			this.orderItems = Collections.unmodifiableMap(orderItems);
		}
	}

	public Map<Product, Integer> getOrderItems() {
		return orderItems;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}