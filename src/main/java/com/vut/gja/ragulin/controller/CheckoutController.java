package com.vut.gja.ragulin.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vut.gja.ragulin.web.domain.Basket;
import com.yummynoodlebar.persistence.domain.Customer;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.services.CustomerPersistenceService;
import com.yummynoodlebar.persistence.services.OrderPersistenceService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	private static final Logger LOG = LoggerFactory
			.getLogger(BasketCommandController.class);

	@Autowired
	private Basket basket;

	@Autowired
	private OrderPersistenceService orderService;

	@Autowired
	private CustomerPersistenceService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public String checkout() {
		return "/checkout";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doCheckout(
			@Valid @ModelAttribute("customerInfo") Customer customer,
			BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			// errors in the form
			// show the checkout form again
			System.out.println("errors in submitted customer info");
			return "/checkout";
		}
		customerService.createCustomer(customer);
		Order order = basket.createOrderDetailsWithCustomerInfo(customer);
		order.setPaymentMethod(customer.getPaymentMethod());
		orderService.createOrder(order);

		String id = order.getId();

		redirectAttrs.addFlashAttribute("message",
				"Your order has been accepted!");

		basket.clear();
		LOG.debug("Basket now has {} items", basket.getSize());

		return "redirect:/order/" + id;
	}

	@ModelAttribute("customerInfo")
	private Customer getCustomerInfo() {
		System.out.println("GETTING CUSTOMER INFO");
		return new Customer();
	}

	@ModelAttribute("basket")
	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
}