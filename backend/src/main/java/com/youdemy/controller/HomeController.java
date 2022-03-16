package com.youdemy.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.youdemy.model.Course;
import com.youdemy.model.Lesson;
import com.youdemy.model.OrderP;
import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;
import com.youdemy.service.CourseService;
import com.youdemy.service.LessonService;
import com.youdemy.service.UserService;
import com.youdemy.service.OrderPService;


@Controller
public class HomeController {
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private LessonService videoService;
	
	@Autowired
	private OrderPService orderService;
	
	@Autowired
	private UserRepository userRepository;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			Optional<User> user = userRepository.findByFirstName(principal.getName());

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("userId", user.get().getId());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			model.addAttribute("teacher", request.isUserInRole("TEACHER"));
			model.addAttribute("user", request.isUserInRole("USER"));
			model.addAttribute("isTeacherOrAdmin", (request.isUserInRole("ADMIN") || request.isUserInRole("TEACHER")));

		} else {
			model.addAttribute("logged", false);
		}
	}


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }
    
    @GetMapping("/admin")
    public String admin(Model model) {
    	
    	ArrayList<OrderP> orders = (ArrayList<OrderP>) orderService.findAll();
    	ArrayList<User> users = (ArrayList<User>) userService.findAll();
    	ArrayList<Course> courses = (ArrayList<Course>) courseService.findAll();
    	ArrayList<Lesson> lessons = (ArrayList<Lesson>) videoService.findAll();
    	
    	model.addAttribute("orders", orders);
    	model.addAttribute("users", users);
    	model.addAttribute("lessons", lessons);
    	model.addAttribute("courses", courses);
    	
    	
        return "admin";
    }

}