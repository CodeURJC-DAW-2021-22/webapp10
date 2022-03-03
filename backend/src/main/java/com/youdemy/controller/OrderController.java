package com.youdemy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youdemy.model.Order;
import com.youdemy.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	
	@GetMapping("/orders")
	public String showOrders(Model model) {
		model.addAttribute("orders", orderService.findAll());
		model.addAttribute("ordertab", true);
		return "admin";	
	}
	
	@GetMapping("/orders/{id}")
	public String showOrder(Model model, @PathVariable long id) {
		
		Optional<Order> order = orderService.findById(id);
		if (order.isPresent()) {
			model.addAttribute("order", order.get());
		} 
		return "order";

	}
}
