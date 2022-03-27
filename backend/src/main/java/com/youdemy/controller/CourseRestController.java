package com.youdemy.controller;

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

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youdemy.model.Course;
import com.youdemy.model.Lesson;
import com.youdemy.model.User;
import com.youdemy.security.jwt.UserLoginService;
import com.youdemy.service.CourseService;
import com.youdemy.service.UserService;


@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@GetMapping("/")
	public Collection<Course> getCourses() {
		return courseService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable long id){
		Optional<Course> op = courseService.findById(id);
		if (op.isPresent()) {
			Course course = op.get();
			return new ResponseEntity<>(course, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
//	@PostMapping("/")
//	public ResponseEntity<Course> createcourse(@RequestBody Course course) {
//
//		courseService.save(course);
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
//		
//		return ResponseEntity.created(location).body(course);
//	}
	
	@PostMapping("/")
	public String postNewCourse(@RequestBody Course newCourse, Model model) throws IOException {
		
		
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("aquiiiiiiiiiiii  "+authentication.getName());
		
		
		
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		String username = userDetails.getUsername();
		
		System.out.println("aquiiiiiiiiiiii  "+username);
		
		User author = userService.findByFirstName(Objects.requireNonNull(authentication.getName()));
		
		
		newCourse.getLessons().forEach(lesson -> {
					lesson.setAuthor(author);
					lesson.setCourse(newCourse);
		});

		newCourse.setAuthor(author);
		newCourse.setThumbnail(loadRandomImage());
	

		courseService.save(newCourse);
		
		System.out.println(newCourse.getLessons());
		return "redirect:/";
		
	}
	
	public byte[] loadRandomImage() throws IOException {
		int randomImgNum = (int) Math.floor(Math.random() * 9) + 1;
		File image = ResourceUtils.getFile("classpath:./fakeImages/" + randomImgNum + ".jpg");

		return Files.readAllBytes(image.toPath());
	}
	
	
	

	
}
