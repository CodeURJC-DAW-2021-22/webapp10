package com.youdemy.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.youdemy.model.Course;
import com.youdemy.model.OrderP;
import com.youdemy.model.User;
import com.youdemy.repository.OrderPRepository;
import com.youdemy.service.CourseService;
import com.youdemy.service.OrderPService;
import com.youdemy.service.UserService;

import antlr.collections.List;


@Controller
@RequestMapping("/orders")
public class OrderPController {
	
	@Autowired
	private OrderPService orderService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderPRepository orderRepository;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			model.addAttribute("teacher", request.isUserInRole("TEACHER"));
			model.addAttribute("user", request.isUserInRole("USER"));
			model.addAttribute("isTeacherOrAdmin", (request.isUserInRole("ADMIN") || request.isUserInRole("TEACHER")));

		} else {
			model.addAttribute("logged", false);
		}
	}
	
	@GetMapping(value = {
			"/",
			""
	})
	public String showOrders(Model model) {
		model.addAttribute("orders", orderService.findAll());
		model.addAttribute("ordertab", true);
		
		return "admin";	
	}
	
	@GetMapping("/{id}")
	public String showOrder(Model model, @PathVariable long id) {
		
		Optional<OrderP> order = orderService.findById(id);
		OrderP dbOrder = order.get();
		Optional<User> user = userService.findById(dbOrder.getId());
		Optional<Course> course = courseService.findById(dbOrder.getId());
		
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
	
	@PostMapping("/new")
	public String createOrder(Model model, @RequestParam long userId , @RequestParam int price, @RequestParam long courseId) throws IOException {

		Optional<OrderP> order = Optional.ofNullable(new OrderP());
		Optional<User> user = userService.findById(userId);
		Optional<Course> course = courseService.findById(courseId);
		
		if (!user.isPresent()) {
			user = userService.findById(1); // guest customer case
		}
		
		User dbUser = user.get();
		Course dbCourse = course.get();
		OrderP dbOrder= order.get();
			
		dbOrder.setUser(dbUser.getId());
		dbOrder.setCourse(dbCourse.getId());
		dbOrder.setPrice(price);
		orderRepository.save(dbOrder);
		
		model.addAttribute("order", dbOrder);
		model.addAttribute("user", dbUser);
		model.addAttribute("course", dbCourse);
		
		long id = dbOrder.getId();
		
		return "redirect:/orders/"+id;
	}
	
	@GetMapping("/remove/{id}")
	public String removeOrder(Model model, @PathVariable long id) {

		Optional<OrderP> order = orderService.findById(id);
		if (order.isPresent()) {
			orderService.delete(id);
			model.addAttribute("order", order.get());
		}
		return "removedorder";
	}
		
	@PostMapping("/edit/{id}")
	public String editorderProcess(Model model, OrderP order)
			throws IOException, SQLException {

		orderService.save(order);

		model.addAttribute("orderId", order.getId());

		return "redirect:/order/"+order.getId();
	}
}