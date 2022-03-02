package com.youdemy.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.youdemy.model.Course;
import com.youdemy.model.Video;
import com.youdemy.service.CourseService;
import com.youdemy.service.VideoService;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private VideoService videoService;

	
	@GetMapping("/courses")
	public String showCourses(Model model) {
		model.addAttribute("courses", courseService.findAll());
		return "courses";	
	}
	
	@GetMapping("/courses/{id}")
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
	public ModelAndView newCourse() {
		ModelAndView view = new ModelAndView("newCourse");

		return view;
	}

}
