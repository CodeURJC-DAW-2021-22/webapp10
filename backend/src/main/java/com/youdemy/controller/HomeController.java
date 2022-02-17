package com.youdemy.controller;

import com.youdemy.model.Course;
import com.youdemy.model.Video;
import com.youdemy.repo.CourseRepository;
import com.youdemy.repo.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	private CourseRepository repository;
	private VideoRepository videoRepository;

	@GetMapping("/")
	public String guardarAnuncio(Model model) {
		Course test = new Course("SQL", "Curso  SQL", "Emiliano", "image.url",100, "tecnologia");
		repository.save(test);
		return "index";
	}

	@GetMapping("/saveVideo")
	public String saveVideo(Model model) {
		Video video = new Video("test","video test","video","video",5,"cocina");
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

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
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
