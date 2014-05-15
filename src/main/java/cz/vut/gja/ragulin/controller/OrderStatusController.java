package cz.vut.gja.ragulin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cz.vut.gja.ragulin.persistence.domain.Order;
import cz.vut.gja.ragulin.persistence.services.OrderPersistenceService;

@Controller
@RequestMapping("/order/{orderId}")
public class OrderStatusController {

	private static final Logger LOG = LoggerFactory
			.getLogger(OrderStatusController.class);

	@Autowired
	private OrderPersistenceService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public String orderStatus(@ModelAttribute("order") Order order) {
		LOG.debug("Get order status for order id {} customer {}",
				order.getId(), order.getOrderStatus());
		return "/order";
	}

	@ModelAttribute("order")
	private Order getOrder(@PathVariable("orderId") int orderId) {
		Order order = orderService.requestOrderDetails(orderId);
		return order;
	}
}