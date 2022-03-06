package com.youdemy.controller;

import java.security.Principal;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.model.Video;
import com.youdemy.repository.UserRepository;
import com.youdemy.service.CourseService;
import com.youdemy.service.VideoService;


@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("/courses")
	public String showCourses(Model model) {
		model.addAttribute("courses", courseService.findAll());
		return "courses";	
	}
	
	@GetMapping("/courses/{id}")
	public String showCourse(Model model, @PathVariable long id, HttpServletRequest request) {
		Optional<Course> course = courseService.findById(id);
		
		Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		Optional<User> user = userRepository.findByFirstName(userName);
		
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			model.addAttribute("user", user.get());
			return "course";
		} else {
			return "redirect:/courses";
		}
	}

}
