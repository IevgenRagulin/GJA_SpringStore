package com.yummynoodlebar.persistence.services;

import java.util.ArrayList;
import java.util.List;

import com.yummynoodlebar.persistence.domain.Review;
import com.yummynoodlebar.persistence.repository.ReviewsRepository;

public class ReviewPersistenceServiceImpl implements ReviewPersistenceService {

	private final ReviewsRepository reviewRepository;

	public ReviewPersistenceServiceImpl(final ReviewsRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Review> requestAllReviews() {
		List<Review> generatedDetails = new ArrayList<Review>();
		for (Review order : reviewRepository.findAll()) {
			generatedDetails.add(order);
		}
		return generatedDetails;
	}

	@Override
	public void addReview(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public Review getReviewById(String reviewId) {
		return reviewRepository.findById(reviewId);
	}

	@Override
	public List<Review> findReviewsForProductWithId(String productId) {
		return reviewRepository.findReviewsForProduct(productId);
	}

}
