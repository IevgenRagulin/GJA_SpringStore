package com.yummynoodlebar.persistence.services;

import java.util.List;

import com.yummynoodlebar.persistence.domain.Review;

public interface ReviewPersistenceService {
	public List<Review> requestAllReviews();

	public void addReview(Review review);

	public Review getReviewById(String reviewId);

	public List<Review> findReviewsForProductWithId(String productId);

}
