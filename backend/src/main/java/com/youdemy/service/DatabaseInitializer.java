package com.youdemy.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.youdemy.model.Lesson;
import com.youdemy.model.VideoThumbnail;
import com.youdemy.repository.VideoThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;
import com.youdemy.repository.OrderRepository;
import org.springframework.util.ResourceUtils;


@Service
public class DatabaseInitializer {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private VideoThumbnailRepository videoThumbnailRepository;
	
	@Autowired
	private LessonService lessonService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws IOException {
		// Sample users
		User user1 = new User("user", "Ramirez","user@mail.com", passwordEncoder.encode("pass"), "USER");
		User user2 = new User("admin", "Ramirez","user@mail.com", passwordEncoder.encode("adminpass"), "USER", "ADMIN");
		User user3 = new User("teacher", "Ramirez","user@mail.com", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		// Sample course
		for (int i = 0; i < 30; i++) {
			byte[] thumbnail = loadRandomImage();

			ArrayList<String> tags = new ArrayList<>();
			tags.add("Tag1");
			tags.add("Tag2");

			Course course = new Course("Java", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user1);
			courseService.save(course);
		}
		
		/*ArrayList<Course> courses = new ArrayList<Course>();
		courses.add(sql); 
		Order order1 = new Order();
		order1.setPrice(10);
		order1.setUser(user3);
		order1.setCourses(courses);
		orderRepository.save(order1);*/
		
		//order1.setCourses(sql);
	}

	public byte[] loadRandomImage() throws IOException {
		int randomImgNum = (int) Math.floor(Math.random() * 9) + 1;
		File image = ResourceUtils.getFile("classpath:./fakeImages/" + randomImgNum + ".jpg");

		return Files.readAllBytes(image.toPath());
	}

}
