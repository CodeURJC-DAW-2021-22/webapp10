package com.youdemy;

import java.io.IOException;
import java.sql.Blob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import antlr.collections.List;

@Controller
public class WebController {
	
	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String guardarAnuncio(Model model) {
		Course test = new Course("SQL", "Curso  SQL", "Emiliano", "image.url",100, "tecnologia");
		repository.save(test);
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {		
		return "admin";	
	}
	
	@GetMapping("/newvideo")
	public String newVideo(Model model) {
		return "newVideo";		
	}
	
	@PostMapping("/register")
	public String createUser(Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String role, @RequestParam String userEmail, @RequestParam String userPassword) {
		User user = new User(firstName, lastName, userEmail, userPassword, role);
		userRepository.save(user);
		return "login";		
	}
	
	@GetMapping("/login")
	public String login(Model model) {		
		return "login";		
	}
	
	@GetMapping("/itemList")
	public String itemList(Model model) {			
		return "itemList";		
	}

	@PostMapping("/newvideo")
	public String createVideo(Model model, @RequestParam String title , @RequestParam MultipartFile imageField, @RequestParam String author, @RequestParam String description, @RequestParam int duration, @RequestParam String course) throws IOException {
		
		Video video = new Video(title, description);
		
		video.setAuthor(author);
		video.setDuration(duration);
		video.setCourse(course);
		
		if (!imageField.isEmpty()) {
			video.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
		}
		
		videoRepository.save(video);
		
		return "newVideo";
		
		
		//return "redirect:/video/"+video.getId();
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
