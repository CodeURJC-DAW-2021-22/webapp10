package com.youdemy.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.model.Video;
import com.youdemy.repository.CourseRepository;
import com.youdemy.repository.UserRepository;
import com.youdemy.repository.VideoRepository;
import com.youdemy.service.CourseService;


@Controller
public class HomeController {
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}


	@GetMapping("/")
	public String home() {
		return "index";
	}

}
