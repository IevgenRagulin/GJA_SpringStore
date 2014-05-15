package cz.vut.gja.ragulin.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import cz.vut.gja.ragulin.config.JPAConfiguration;

@Entity(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID", nullable = false)
	private int id;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private Double productPrice;
	private String averageRating;
	private int deliveryTime = JPAConfiguration.getRandomDeliveryDays();
	private int inStock;

	public Product() {
	}

	public Product(int key) {
		this.id = key;
	}

	public String getProductName() {
		System.out.println("GETTING PRODUCT NAME" + productName);
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
