package com.yummynoodlebar.persistence.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "PRODUCTS")
public class Product {

	@Id
	@Column(name = "PRODUCT_ID", nullable = false)
	private String id;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private Double productPrice;

	public Product() {
		this.id = UUID.randomUUID().toString();
	}

	public Product(String key) {
		this.id = key;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getId() {
		return id;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

}
