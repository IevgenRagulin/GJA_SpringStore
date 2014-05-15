package cz.vut.gja.ragulin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.vut.gja.ragulin.persistence.domain.Product;
import cz.vut.gja.ragulin.persistence.services.ProductPersistenceService;

@Controller
public class AllProductsController {

	@Autowired
	private ProductPersistenceService productsService;

	@RequestMapping(value = "/allproducts", method = RequestMethod.GET)
	public String allProducts(Model model) {
		model.addAttribute("productItems", productsService.requestAllProducts());
		return "/allproducts";
	}

	@RequestMapping(value = "/addNewProduct", method = RequestMethod.POST)
	public String addNewProduct(Model model,
			@Valid @ModelAttribute("newProductInfo") Product product,
			BindingResult result, RedirectAttributes redirectAttrs) {
		productsService.addProduct(product);
		model.addAttribute("productItems", productsService.requestAllProducts());
		return "/allproducts";
	}

	@RequestMapping(value = "/modifyProduct", method = RequestMethod.POST)
	public String modifyProduct(Model model,
			@Valid @ModelAttribute("productInfo") Product product,
			BindingResult result, RedirectAttributes redirectAttrs) {
		productsService.addProduct(product);
		model.addAttribute("productItems", productsService.requestAllProducts());
		return "/allproducts";
	}

	@ModelAttribute("newProductInfo")
	private Product getNewProductInfo() {
		return new Product();
	}

	@ModelAttribute("existingProductInfo")
	private Product getExistingProductInfo() {
		return new Product();
	}
}
