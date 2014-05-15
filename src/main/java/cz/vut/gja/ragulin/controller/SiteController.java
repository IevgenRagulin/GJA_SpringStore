package cz.vut.gja.ragulin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.vut.gja.ragulin.persistence.repository.ProductRepository;
import cz.vut.gja.ragulin.persistence.services.ProductPersistenceServiceImpl;
import cz.vut.gja.ragulin.web.domain.Basket;

@Controller
@RequestMapping("/")
public class SiteController {

	@Autowired
	private ProductPersistenceServiceImpl productsService;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private Basket basket;

	@RequestMapping(method = RequestMethod.GET)
	public String getCurrentProducts(Model model) {
		model.addAttribute("productItems", productsService.requestAllProducts());
		return "/home";
	}

	@ModelAttribute("basket")
	private Basket getBasket() {
		return basket;
	}

}