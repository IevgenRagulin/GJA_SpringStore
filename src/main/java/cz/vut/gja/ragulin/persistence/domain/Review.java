package cz.vut.gja.ragulin.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "REVIEWS")
public class Review {

	@Id
	@GeneratedValue
	@Column(name = "REVIEW_ID", nullable = false)
	private int id;

	private String name;

	@Column(nullable = false)
	private int rating;

	@Column(nullable = false)
	private String review;

	@ManyToOne
	private Product product;

	public Review() {
	}

	public Review(int key) {
		this.id = key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
