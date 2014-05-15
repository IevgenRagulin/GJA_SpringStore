package cz.vut.gja.ragulin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import cz.vut.gja.ragulin.persistence.domain.Order;
import cz.vut.gja.ragulin.persistence.domain.OrderStatus;
import cz.vut.gja.ragulin.persistence.services.OrderPersistenceEventHandler;

@Controller
public class AllOrdersController {
	@Autowired
	private OrderPersistenceEventHandler ordersService;

	@RequestMapping(value = "/allorders", method = RequestMethod.GET)
	public String allOrdersStatus(Model model) {
		model.addAttribute("orderItems", ordersService.requestAllOrders());
		return "/allorders";
	}

	@RequestMapping(value = "/setOrderStatusAwaitingPayment", method = RequestMethod.POST)
	public String awaitingPayment(Model model,
			@ModelAttribute("fred") Order order) {
		ordersService.setOrderStatus(order.getId(),
				OrderStatus.AWAITING_PAYMENT);
		model.addAttribute("orderItems", ordersService.requestAllOrders());
		return "/allorders";
	}

	@RequestMapping(value = "/setOrderStatusBeingDelivered", method = RequestMethod.POST)
	public String beingDelivered(Model model,
			@ModelAttribute("fred") Order order) {
		ordersService
				.setOrderStatus(order.getId(), OrderStatus.BEING_DELIVERED);
		model.addAttribute("orderItems", ordersService.requestAllOrders());
		return "/allorders";
	}

	@RequestMapping(value = "/setOrderStatusDelivered", method = RequestMethod.POST)
	public String delivered(Model model, @ModelAttribute("fred") Order order) {
		ordersService.setOrderStatus(order.getId(), OrderStatus.DELIVERED);
		model.addAttribute("orderItems", ordersService.requestAllOrders());
		return "/allorders";
	}

	@RequestMapping(value = "/setOrderStatusOther", method = RequestMethod.POST)
	public String other(Model model, @ModelAttribute("fred") Order order) {
		ordersService.setOrderStatus(order.getId(), OrderStatus.OTHER);
		model.addAttribute("orderItems", ordersService.requestAllOrders());
		return "/allorders";
	}
}
