package com.youdemy.controller;

import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.model.Video;
import com.youdemy.repository.CourseRepository;
import com.youdemy.repository.UserRepository;
import com.youdemy.repository.VideoRepository;
import com.youdemy.service.CourseService;


@Controller
public class WebController {
	
	@Autowired
	private CourseRepository repository;
	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseService courseService;
	
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
	public String guardarAnuncio(Model model) {
		Course test = new Course("SQL", "Curso  SQL", "Emiliano", "image.url",100, "tecnologia");
		repository.save(test);
		return "index";
	}
	
	@GetMapping("/saveVideo")
	public String saveVideo(Model model) {	
		Video video = new Video("test","video test","video","video",5);
		videoRepository.save(video);
		return "index";
	}
	
	
	
	@GetMapping("/admin")
	public String admin(Model model) {		
		return "admin";	
	}
	
	@GetMapping("/newVideo")
	public String newVideo(Model model) {		
		return "newVideo";		
	}
	
	@GetMapping("/register")
	public String register(Model model) {		
		return "register";		
	}
	
	@PostMapping("/register")
	public String createUser(Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String role, @RequestParam String userEmail, @RequestParam String userPassword) {
		User user = new User(firstName, lastName, userEmail, userPassword, role);
		userRepository.save(user);
		return "login";		
	}
	
	@GetMapping("/itemList")
	public String itemList(Model model) {			
		return "itemList";		
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {		
		return "cart";		
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {		
		return "checkout";		
	}
	
	@GetMapping("/product")
	public String product(Model model) {		
		return "product";		
	}
	
	@GetMapping("/success")
	public String success(Model model) {		
		return "success";		
	}	
}