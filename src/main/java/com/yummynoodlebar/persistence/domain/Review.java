package com.yummynoodlebar.persistence.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "REVIEWS")
public class Review {

	@Id
	@Column(name = "REVIEW_ID", nullable = false)
	private String id;

	private String name;

	@Column(nullable = false)
	private int rating;

	@Column(nullable = false)
	private String review;

	@ManyToOne
	private Product product;

	public Review() {
		this.id = UUID.randomUUID().toString();
	}

	public Review(String key) {
		this.id = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
