package com.youdemy.controller;

import com.youdemy.model.Course;
import com.youdemy.model.CourseBoughtTimes;
import com.youdemy.service.CourseBoughtTimesService;
import com.youdemy.service.CourseService;
import com.youdemy.service.OrderPService;
import com.youdemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.youdemy.model.User;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderPService orderPService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CourseBoughtTimesService courseBoughtTimesService;

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

	@RequestMapping("/myaccount/{id}")
	public String showUserInfo(Model model, @PathVariable long id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if(principal != null) {
			User user = userService.findById(id).get();
			long userId;
			userId = (long) model.getAttribute("userId");

			if (model.getAttribute("admin").equals(false) && userId != id) {
				return "accessDenied";
			}

			if (model.getAttribute("teacher").equals(true)) {
				ArrayList<CourseBoughtTimes> coursesBoughtTimes =
						new ArrayList<>(courseBoughtTimesService.getCourseBoughtTimes(user));

				model.addAttribute("coursesBoughtTimes", coursesBoughtTimes);
			}

			model.addAttribute("orders", orderPService.findByUserId(id));
			model.addAttribute("user", user);
		}
		return "myAccount";
	}
}
