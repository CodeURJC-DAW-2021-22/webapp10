package com.youdemy.controller;

import java.security.Principal;
import java.util.Optional;

import com.youdemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.youdemy.model.Course;
import com.youdemy.service.CourseService;
import com.youdemy.service.VideoService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@Autowired
	UserService userService;
	
	@Autowired
	private VideoService videoService;

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
	
	@GetMapping(value = {
			"/",
			""
	})
	public String showCourses(Model model) {
		model.addAttribute("courses", courseService.findAll());

		return "courses";
	}
	
	@GetMapping("/{id}")
	public String showCourse(Model model, @PathVariable long id) {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			return "course";
		} else {
			return "redirect:/courses";
		}
	}

	@GetMapping("/new")
	public String newCourse(Model model) {
		if (model.getAttribute("logged") == Boolean.valueOf(false))
			return "redirect:/signin";

		return "newCourse";
	}

}
