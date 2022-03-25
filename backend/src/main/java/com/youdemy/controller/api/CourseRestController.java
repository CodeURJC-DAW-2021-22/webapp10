package com.youdemy.controller.api;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.youdemy.controller.BasicAttributes;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youdemy.model.Course;
import com.youdemy.model.Lesson;
import com.youdemy.model.User;
import com.youdemy.service.CourseService;
import com.youdemy.service.UserService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		BasicAttributes.addAttributes(model, request, userService);
	}

	@GetMapping("/{id}")
	public Course getById(@PathVariable long id) {
		Optional<Course> course = courseService.findById(id);

		return course.orElse(null);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable long id) {
		courseService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page<Course> getCoursesInPage(@RequestParam Optional<Integer> page,
										 @RequestParam Optional<String> search) {
		System.out.println(page.orElse(0));

		return courseService.findByTitle(search.orElse(""),
				PageRequest.of(page.orElse(0), 6));
	}

	@PostMapping("/new")
	public Course create(@RequestAttribute("title") String title, @RequestAttribute("image") MultipartFile image,
						 @RequestAttribute("description") String description, @RequestAttribute("price") int price,
						 @RequestAttribute("tags") String tags, @RequestAttribute("lessons") String lessons,
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

		return courseService.findById(course.getId()).get();
	}
	
	public byte[] loadRandomImage() throws IOException {
		int randomImgNum = (int) Math.floor(Math.random() * 9) + 1;
		File image = ResourceUtils.getFile("classpath:./fakeImages/" + randomImgNum + ".jpg");

		return Files.readAllBytes(image.toPath());
	}
	
	
	

	
}
