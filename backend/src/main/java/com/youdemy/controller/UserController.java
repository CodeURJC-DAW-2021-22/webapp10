package com.youdemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youdemy.model.Course;
import com.youdemy.model.OrderP;
import com.youdemy.model.User;
import com.youdemy.repository.CourseRepository;
import com.youdemy.repository.OrderPRepository;
import com.youdemy.repository.UserRepository;
import com.youdemy.service.CourseService;
import com.youdemy.service.UserService;

import antlr.collections.List;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderPRepository orderRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			Optional<User> user = userRepository.findByFirstName(principal.getName());

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("userId", user.get().getId());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}
	
	@RequestMapping("/signin")
	public String signIn(Model model) {
		if (Objects.equals(model.getAttribute("logged"), true)) return "redirect:/";
		return "signin";
	}

	@RequestMapping("/signinError")
	public String signinError() {
		return "signinError";
	}

	@RequestMapping("/signup")
	public String signUp(Model model) {
		if (Objects.equals(model.getAttribute("logged"), true)) return "redirect:/";
		return "signup";
	}

	@RequestMapping("/signupError")
	public String signupError() {
		return "signupError";
	}

	@RequestMapping("/signout")
	public String signOut() {
		return "signout";
	}

	@RequestMapping("/signoutError")
	public String signoutError() {
		return "signoutError";
	}

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/userError")
	public String userError() {
		return "userError";
	}
	
	@PostMapping("/signup")
	public String registerUser(@RequestParam String userFirstName, @RequestParam String userLastName, @RequestParam String userEmail, @RequestParam String userPassword, @RequestParam String userRole) {
		User user = new User(userFirstName, userLastName, userEmail, passwordEncoder.encode(userPassword), userRole);
		userRepository.save(user);
		return "signin";
	}
	
	@RequestMapping("/myaccount/{id}")
	public String showUserInfo(Model model, @PathVariable long id, HttpServletRequest request) {		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			String userName = principal.getName();
			Optional<User> user = userRepository.findByFirstName(userName);
			long userId;
			userId = user.get().getId();
			if (userId != id) {
				return "accessDenied";
			}			
			model.addAttribute("orders", (ArrayList) orderRepository.findByUser(userId));		
			model.addAttribute("user", user.get());			
		}	
		return "myAccount";
	}
}