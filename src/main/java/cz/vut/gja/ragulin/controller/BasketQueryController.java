package cz.vut.gja.ragulin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.vut.gja.ragulin.web.domain.Basket;

@Controller
public class BasketQueryController {

	private static final Logger LOG = LoggerFactory
			.getLogger(BasketQueryController.class);

	@Autowired
	private Basket basket;

	@RequestMapping(value = "/showBasket", method = RequestMethod.GET)
	public String show(Model model) {
		System.out.println("Show basket contents");
		return "/showBasket";
	}

	@ModelAttribute("basket")
	private Basket getBasket() {
		return basket;
	}

}