package com.youdemy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youdemy.model.Course;
import com.youdemy.model.Order;
import com.youdemy.model.User;
import com.youdemy.repository.OrderRepository;
import com.youdemy.service.CourseService;
import com.youdemy.service.OrderService;
import com.youdemy.service.UserService;

import antlr.collections.List;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/orders")
	public String showOrders(Model model) {
		model.addAttribute("orders", orderService.findAll());
		model.addAttribute("ordertab", true);
		
		return "admin";	
	}
	
	@GetMapping("/order/{id}")
	public String showOrder(Model model, @PathVariable long id) {
		
		Optional<Order> order = orderService.findById(id);
		Order dbOrder = order.get();
		Optional<User> user = userService.findById(dbOrder.getUser().getId());
		Optional<Course> course = courseService.findById(dbOrder.getCourse().getId());
		
		if (order.isPresent()) {
			model.addAttribute("order", order.get());
		}
		
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
		}
		
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
		} 
		
		return "order";
	}
	
	@PostMapping("/neworder")
	public String createOrder(Model model, @RequestParam long userId , @RequestParam int price, @RequestParam long courseId) throws IOException {

		Optional<Order> order = Optional.ofNullable(new Order());
		Optional<User> user = userService.findById(userId);
		Optional<Course> course = courseService.findById(courseId);
		
		User dbUser = user.get();
		Course dbCourse = course.get();
		Order dbOrder= order.get();
			
		dbOrder.setUser(dbUser);
		dbOrder.setCourse(dbCourse);
		dbOrder.setPrice(price);
		orderRepository.save(dbOrder);
		
		model.addAttribute("order", dbOrder);
		model.addAttribute("user", dbUser);
		model.addAttribute("course", dbCourse);
		
		long id = dbOrder.getId();
		
		return "redirect:/order/"+id;
	}
	
	@GetMapping("/removeorder/{id}")
	public String removeOrder(Model model, @PathVariable long id) {

		Optional<Order> order = orderService.findById(id);
		if (order.isPresent()) {
			orderService.delete(id);
			model.addAttribute("order", order.get());
		}
		return "removedorder";
	}
		
	@PostMapping("/editorder")
	public String editorderProcess(Model model, Order order)
			throws IOException, SQLException {


		orderService.save(order);

		model.addAttribute("orderId", order.getId());

		return "redirect:/order/"+order.getId();
	}
}