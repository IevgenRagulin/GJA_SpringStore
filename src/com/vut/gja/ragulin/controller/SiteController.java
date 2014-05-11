package com.vut.gja.ragulin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vut.gja.ragulin.web.domain.Basket;
import com.yummynoodlebar.persistence.domain.Product;
import com.yummynoodlebar.persistence.repository.ProductRepository;
import com.yummynoodlebar.persistence.services.ProductPersistenceServiceImpl;

@Controller
@RequestMapping("/")
public class SiteController {

	private static final Logger LOG = LoggerFactory
			.getLogger(SiteController.class);

	@Autowired
	private ProductPersistenceServiceImpl productsService;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private Basket basket;

	private static volatile boolean productsInited = false;

	@RequestMapping(method = RequestMethod.GET)
	public String getCurrentProducts(Model model) {
		if (!productsInited) {
			productsInited = true;
			Product product = new Product("1");
			product.setProductPrice(10.0);
			product.setProductName("T-Shirt with Abbey road image");
			productsService.addProduct(product);
			product = new Product("2");
			product.setProductPrice(20.0);
			product.setProductName("T-Shirt with Rolling stones photo");
			productsService.addProduct(product);
		}
		model.addAttribute("productItems", productsService.requestAllProducts());
		return "/home";
	}

	@ModelAttribute("basket")
	private Basket getBasket() {
		return basket;
	}

}