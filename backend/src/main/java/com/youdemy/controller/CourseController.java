package com.youdemy.controller;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youdemy.model.Lesson;
import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;
import com.youdemy.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	@Autowired
	private UserRepository userRepository;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			model.addAttribute("teacher", request.isUserInRole("TEACHER"));
			model.addAttribute("user", request.isUserInRole("USER"));
			model.addAttribute("isTeacherOrAdmin", (request.isUserInRole("ADMIN") || request.isUserInRole("TEACHER")));

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
		Page<Course> courses = courseService.findByTitle(search.orElse(""),
				PageRequest.of(0, 6));

		model.addAttribute("courses", courses);
		model.addAttribute("search", search.orElse(null));
		model.addAttribute("numResults", courses.getTotalElements());
		model.addAttribute("totalPages", courses.getTotalPages());

		return "courses";
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page<Course> getCoursesInPage(@RequestParam Optional<Integer> page,
								   @RequestParam Optional<String> search) {
		System.out.println(page.orElse(0));

		return courseService.findByTitle(search.orElse(""),
				PageRequest.of(page.orElse(0), 6));
	}
	
	@RequestMapping(value = "/getCurrentCourse", method = RequestMethod.GET)
	@ResponseBody
	public Page<Course> getCurrentCourse(@RequestParam Optional<Integer> page,
								   @RequestParam Optional<String> search) {
		System.out.println(page.orElse(0));

		return courseService.findByTitle(search.orElse(""),
				PageRequest.of(page.orElse(0), 6));
	}
	
	
	@GetMapping("/{id}")
	public String showCourse(Model model, @PathVariable long id, HttpServletRequest request) {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			Principal principal = request.getUserPrincipal();
			if(principal != null) {
				String userName = principal.getName();
				Optional<User> user = userRepository.findByFirstName(userName);
				long userId;
				userId = user.get().getId();
				model.addAttribute("userId", userId);
				if(course.get().getAuthor().getId() == userId) {
					model.addAttribute("owner", true);
				}
				
			}
			
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
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable long id)  {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			model.addAttribute("course", course.get());
			return "editCourse";
		} else {
			return "redirect:/courses";
		}
	}

	@PostMapping("/update")
	public String updateCourse(@RequestParam("id") long id, @RequestParam("title") String title, @RequestParam("image") MultipartFile image,
								@RequestParam("description") String description, @RequestParam("price") int price,
								@RequestParam("tags") String tags, @RequestParam("lessons") String lessons,
								Model model) throws IOException {
		Optional<Course> courseOptional = courseService.findById(id);
		
		if (courseOptional.isPresent()) {
			Course course = courseOptional.get();
			
			User author = userService.findByFirstName(Objects.requireNonNull(model.getAttribute("userName")).toString());
			
			List<Lesson> lessonList = new ArrayList<>(Arrays.asList(new ObjectMapper().readValue(lessons, Lesson[].class)));
			lessonList.forEach(lesson -> {
						lesson.setAuthor(author);
						lesson.setCourse(course);
			});
			
			course.setThumbnail(image.getBytes());
			course.setTitle(title);
			course.setDescription(description);
			course.setPrice(price);
			course.setTags(new ArrayList(Arrays.asList(new ObjectMapper().readValue(tags, String[].class))));
			course.setLessons(lessonList);

			courseService.save(course);
			return "redirect:/courses";
		} else {
			return "redirect:/courses";
		}
		
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable long id, HttpServletRequest request)  {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			Course curr = course.get();
			for(Lesson l : curr.getLessons()) {
				videoService.delete(l.getId());
			}
			courseService.delete(id);
			return "redirect:/courses";
		} else {
			return "redirect:/courses";
		}
	}
	

	
	
	

}
