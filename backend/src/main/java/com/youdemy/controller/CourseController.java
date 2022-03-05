package com.youdemy.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youdemy.model.Lesson;
import com.youdemy.model.User;
import com.youdemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.youdemy.model.Course;
import com.youdemy.service.CourseService;
import com.youdemy.service.LessonService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@Autowired
	UserService userService;
	
	@Autowired
	private LessonService videoService;

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
	public String showCourses(Model model,
							  @RequestParam Optional<String> search) {
		List<Course> courses = courseService.findByTitle(search.orElse(""));

		model.addAttribute("courses", courses);
		model.addAttribute("search", search.orElse(""));

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

	@GetMapping("/thumbnail/{id}")
	public @ResponseBody byte[] getThumbnail(Model model, @PathVariable String id) {
		Optional<Course> course = courseService.findById(Long.parseLong(id));
		return course.map(Course::getThumbnail).orElse(null);
	}

	@PostMapping("/new")
	public String postNewCourse(@RequestParam("title") String title, @RequestParam("image") MultipartFile image,
								@RequestParam("description") String description, @RequestParam("price") int price,
								@RequestParam("tags") String tags, @RequestParam("lessons") String lessons,
								Model model) throws IOException {
		Course course = new Course();
		User author = userService.findByFirstName(Objects.requireNonNull(model.getAttribute("userName")).toString());

		List<Lesson> lessonList = new ArrayList<>(Arrays.asList(new ObjectMapper().readValue(lessons, Lesson[].class)));
		lessonList.forEach(lesson -> {
					lesson.setAuthor(author);
					lesson.setCourse(course);
		});

		course.setAuthor(author);
		course.setThumbnail(image.getBytes());
		course.setTitle(title);
		course.setDescription(description);
		course.setPrice(price);
		course.setTags(new ArrayList(Arrays.asList(new ObjectMapper().readValue(tags, String[].class))));
		course.setLessons(lessonList);

		courseService.save(course);
		return "redirect:/courses";
	}

}
