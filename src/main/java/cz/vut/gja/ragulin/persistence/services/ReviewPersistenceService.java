package cz.vut.gja.ragulin.persistence.services;

import java.util.List;

import cz.vut.gja.ragulin.persistence.domain.Review;

public interface ReviewPersistenceService {
	public List<Review> requestAllReviews();

	public void addReview(Review review);

	public Review getReviewById(int reviewId);

	public List<Review> findReviewsForProductWithId(int productId);

}
