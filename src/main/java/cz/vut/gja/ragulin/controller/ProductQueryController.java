package cz.vut.gja.ragulin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.vut.gja.ragulin.persistence.domain.Product;
import cz.vut.gja.ragulin.persistence.domain.Review;
import cz.vut.gja.ragulin.persistence.repository.ProductRepository;
import cz.vut.gja.ragulin.persistence.services.ProductPersistenceServiceImpl;
import cz.vut.gja.ragulin.persistence.services.ReviewPersistenceService;
import cz.vut.gja.ragulin.web.domain.Basket;

@Controller
@RequestMapping("/product/{productId}")
public class ProductQueryController {
	@Autowired
	private ProductPersistenceServiceImpl productsService;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private Basket basket;
	@Autowired
	private ReviewPersistenceService reviewService;

	@RequestMapping(method = RequestMethod.GET)
	public String getProduct(@ModelAttribute("product") Product product,
			Model model) {
		model.addAttribute("reviewItems",
				reviewService.findReviewsForProductWithId(product.getId()));
		product.setAverageRating(getAverageRatingForProduct(product.getId()));
		return "/product";
	}

	private String getAverageRatingForProduct(int productId) {
		List<Review> reviews = reviewService
				.findReviewsForProductWithId(productId);
		String averageRating = null;
		int sum = 0;
		int numOfReviews = 0;
		numOfReviews = reviews.size();

		for (Review review : reviews) {
			sum += review.getRating();
		}
		if (numOfReviews == 0) {
			averageRating = "There are no ratings for this product";
		} else {
			Double averageDouble = Math
					.round((sum * 1.0 / numOfReviews) * 100.0) / 100.0;
			averageRating = averageDouble.toString();
		}
		return averageRating;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addReview(@Valid @ModelAttribute("review") Review review,
			@ModelAttribute("product") Product product, BindingResult result,
			RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			// errors in the form
			// show the product page again
			return "/product";
		}
		review.setProduct(product);
		reviewService.addReview(review);
		return "redirect:/product/" + product.getId();
	}

	@ModelAttribute("product")
	private Product getProduct(@PathVariable("productId") int productId) {
		Product product = productsService.getProductById(productId);
		return product;
	}

	@ModelAttribute("review")
	private Review getReview() {
		System.out.println("GETTING REVIEW");
		return new Review();
	}

	@ModelAttribute("basket")
	private Basket getBasket() {
		return basket;
	}
}
