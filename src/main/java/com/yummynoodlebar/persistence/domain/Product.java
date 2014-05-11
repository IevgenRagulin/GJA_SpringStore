package com.yummynoodlebar.persistence.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.yummynoodlebar.config.JPAConfiguration;

@Entity(name = "PRODUCTS")
public class Product {

	@Id
	@Column(name = "PRODUCT_ID", nullable = false)
	private String id;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private Double productPrice;
	private String averageRating;
	private int deliveryTime = JPAConfiguration.getRandomDeliveryDays();
	private int inStock = JPAConfiguration.getRandomInStock();

	public Product() {
		this.id = UUID.randomUUID().toString();
	}

	public Product(String key) {
		this.id = key;
	}

	public String getProductName() {
		System.out.println("GETTING PRODUCT NAME" + productName);
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

}
