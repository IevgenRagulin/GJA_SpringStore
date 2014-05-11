package com.vut.gja.ragulin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vut.gja.ragulin.web.domain.Basket;
import com.yummynoodlebar.persistence.domain.Product;

@Controller
public class BasketCommandController {

	private static final Logger LOG = LoggerFactory
			.getLogger(BasketCommandController.class);

	@Autowired
	private Basket basket;

	@RequestMapping(value = "/removeFromBasket", method = RequestMethod.POST)
	public String remove(@ModelAttribute("fred") Product productItem) {
		System.out.println("Remove " + productItem.getId());
		basket.delete(productItem.getId());
		return "redirect:/showBasket";
	}

	@RequestMapping(value = "/addToBasket", method = RequestMethod.POST)
	public String add(@ModelAttribute("joe") Product productItem) {
		System.out.println("Add " + productItem.getId()
				+ productItem.getProductName() + productItem.getProductPrice());
		basket.add(productItem);
		return "redirect:/";
	}

	@ModelAttribute("basket")
	private Basket getBasket() {
		return basket;
	}

}