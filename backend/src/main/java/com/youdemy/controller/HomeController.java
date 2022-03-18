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

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		BasicAttributes.addAttributes(model, request, userService);
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