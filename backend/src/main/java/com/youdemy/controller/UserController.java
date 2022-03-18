package com.youdemy.controller;

import com.youdemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		BasicAttributes.addAttributes(model, request, userService);
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
		userService.save(user);
		return "signin";
	}

}
