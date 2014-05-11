package com.vut.gja.ragulin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/allorders")
public class AllOrdersController {

	@RequestMapping(method = RequestMethod.GET)
	public String allOrdersStatus() {
		return "/allorders";
	}
}
