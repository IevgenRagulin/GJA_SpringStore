package cz.vut.gja.ragulin.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.vut.gja.ragulin.persistence.domain.Review;

public interface ReviewsRepository extends CrudRepository<Review, String> {
	Review findById(int key);

	@Query(value = "select * from REVIEWS where product_PRODUCT_ID = :productId", nativeQuery = true)
	List<Review> findReviewsForProduct(@Param("productId") int productId);
	// {!end top}
}
