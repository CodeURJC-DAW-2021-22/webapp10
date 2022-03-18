package com.youdemy.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lowagie.text.DocumentException;
import com.youdemy.model.Course;
import com.youdemy.model.OrderP;
import com.youdemy.model.User;
import com.youdemy.repository.OrderPRepository;
import com.youdemy.repository.UserRepository;
import com.youdemy.service.CourseService;
import com.youdemy.service.OrderPDFExporter;
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
	private UserRepository userRepository;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		BasicAttributes.addAttributes(model, request, userService);
	}
	
	@GetMapping(value = {
			"/",
			""
	})
	public String showOrders(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {      //checkin if user registered can is trying to access to other users orders
			String userName = principal.getName();
			Optional<User> pUser = userRepository.findByFirstName(userName);
			long userId = pUser.get().getId();
			model.addAttribute("orders", orderService.findByUserId(userId));
		}
		
		return "orders";	
	}
	
	@GetMapping("/{id}")
	public String showOrder(Model model, @PathVariable long id, HttpServletRequest request) {
		
		Optional<OrderP> order = orderService.findById(id);
		Principal principal = request.getUserPrincipal();
		
		if (order.isPresent()) {
			OrderP dbOrder = order.get();
			Optional<User> user = userService.findById(dbOrder.getUser());
			Optional<Course> course = courseService.findById(dbOrder.getCourse());
			if (principal != null) {                       //checkin if user registered can is trying to access to other users orders
				String userName = principal.getName();
				Optional<User> pUser = userRepository.findByFirstName(userName);
				long userId = pUser.get().getId();
				if (dbOrder.getUser()!= userId ) {
					return "accessDenied";
				}
			}
			model.addAttribute("order", order.get());
			
			if (user.isPresent()) {
				model.addAttribute("user", user.get());
			}
			
			if (course.isPresent()) {
				model.addAttribute("course", course.get());
			}		
		}else {
			return "notPresent"; // trying access to not present resource
		}
		
		return "order";
	}
	
	@PostMapping("/new")
	public String createOrder(Model model, @RequestParam long userId , 
			@RequestParam int price, @RequestParam long courseId,
			@RequestParam String uname,
			@RequestParam String ctitle,
			@RequestParam String billingAddress, 
			@RequestParam String paymentMethod, 
			@RequestParam String country, @RequestParam String region, 
			@RequestParam String expiration,
			@RequestParam String cvv, @RequestParam String ccnumber) throws IOException {

		Optional<OrderP> order = Optional.ofNullable(new OrderP());
		Optional<User> user = userService.findById(userId);
		Optional<Course> course = courseService.findById(courseId);
		
		User dbUser = user.get();
		Course dbCourse = course.get();
		OrderP dbOrder= order.get();
			
		dbOrder.setUser(dbUser.getId());
		dbOrder.setCourse(dbCourse.getId());
		dbOrder.setPrice(price);
		dbOrder.setbillingAddres(billingAddress);
		dbOrder.setCountry(country);
		dbOrder.setPaymentMethod(paymentMethod);
		dbOrder.setRegion(region);
		dbOrder.setDate();
		dbOrder.setUserName(uname);
		dbOrder.setCourseTitle(ctitle);
		dbOrder.setDataCard(ccnumber);
		orderService.save(dbOrder);
		
		model.addAttribute("order", dbOrder);
		model.addAttribute("user", dbUser);
		model.addAttribute("course", dbCourse);
		
		long id = dbOrder.getId();
		
		return "redirect:/orders/success/"+id;
	}
	
	@GetMapping("/remove/{id}")
	public String removeOrder(Model model, @PathVariable long id) {

		Optional<OrderP> order = orderService.findById(id);
		OrderP dbOrder = order.get();
		if (order.isPresent()) {
			orderService.delete(dbOrder.getId());
			model.addAttribute("order", dbOrder);
		}
		return "removeOrder";
	}
	
	 @GetMapping("/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	    response.setContentType("application/pdf");
	    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	    String currentDateTime = dateFormatter.format(new Date(System.currentTimeMillis()));
	         
	    String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename=orders_" + currentDateTime + ".pdf";
	    response.setHeader(headerKey, headerValue);
	         
	    ArrayList<OrderP> orders = (ArrayList<OrderP>) orderService.findAll();
	         
	    OrderPDFExporter exporter = new OrderPDFExporter(orders);
	    exporter.export(response);
	}
	 
	 @GetMapping("/export/pdf/{id}")
		public void exportOrderToPDF(HttpServletResponse response, @PathVariable long id) throws DocumentException, IOException {
		    response.setContentType("application/pdf");
		    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		    String currentDateTime = dateFormatter.format(new Date(System.currentTimeMillis()));
		         
		    String headerKey = "Content-Disposition";
		    String headerValue = "attachment; filename=orders_" + currentDateTime + ".pdf";
		    response.setHeader(headerKey, headerValue);
		         
		    Optional<OrderP> order = orderService.findById(id);
		    OrderP dbOrder = order.get();
		         
		    OrderPDFExporter exporter = new OrderPDFExporter(dbOrder);
		    exporter.orderExport(response);
		}
	 
	 @PostMapping("/checkout")
		public String checkout(Model model, @RequestParam long userId , 
				@RequestParam int price, @RequestParam long courseId) {
		 
		 Optional<Course> course = courseService.findById(courseId);
			
		 Course dbCourse = course.get();
		 model.addAttribute("userId", userId);
		 model.addAttribute("price", price);
		 model.addAttribute("course", dbCourse);
		return "checkout";
	}
	 
	 @GetMapping("/success/{id}")
		public String successPage(Model model, @PathVariable long id) {

			Optional<OrderP> order = orderService.findById(id);
			OrderP dbOrder = order.get();
			
			model.addAttribute("order", dbOrder);
			
			return "success";
		}
		
}