package com.youdemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
	
	@Autowired
	private CourseRepository repository;

	@GetMapping("/")
	public String guardarAnuncio(Model model) {
		
		Course test = new Course("SQL", "Curso  SQL", "Emiliano", "image.url",100, "tecnologia");
		repository.save(test);

		return "index";
		
		
	}
}


