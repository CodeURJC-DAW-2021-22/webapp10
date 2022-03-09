package com.youdemy.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.youdemy.model.Lesson;
import com.youdemy.model.OrderP;
import com.youdemy.model.VideoThumbnail;
import com.youdemy.repository.VideoThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;
import com.youdemy.repository.OrderPRepository;

import org.springframework.util.ResourceUtils;


@Service
public class DatabaseInitializer {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private OrderPService orderService;

	@Autowired
	private VideoThumbnailRepository videoThumbnailRepository;
	
	@Autowired
	private LessonService lessonService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderPRepository orderRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws IOException {
		// Sample users
		User guest = new User("guest", "Guest","guest@mail.com", passwordEncoder.encode("pass2"), "USER");
		User user1 = new User("user", "Ramirez","user@mail.com", passwordEncoder.encode("pass"), "USER");
		User user2 = new User("admin", "Ramirez","user@mail.com", passwordEncoder.encode("adminpass"), "USER", "ADMIN");
		User user3 = new User("teacher", "Ramirez","user@mail.com", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");
		
		
		userRepository.save(guest);
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
		
			
		// Sample orders
				OrderP order1 = new OrderP(user1.getId(),10,1);
				orderRepository.save(order1);
    
        OrderP order2 = new OrderP(user2.getId(),20,2);
				orderRepository.save(order2);
		
				OrderP order3 = new OrderP(user3.getId(),30,3);
				orderRepository.save(order3);
		
				OrderP order4 = new OrderP(user1.getId(),40,4);
				orderRepository.save(order4);
							
				OrderP order5 = new OrderP(user2.getId(),50,5);
				orderRepository.save(order5);
		
				OrderP order6 = new OrderP(user3.getId(),60,6);
				orderRepository.save(order6);
	}

	public byte[] loadRandomImage() throws IOException {
		int randomImgNum = (int) Math.floor(Math.random() * 9) + 1;
		File image = ResourceUtils.getFile("classpath:./fakeImages/" + randomImgNum + ".jpg");

		return Files.readAllBytes(image.toPath());
	}
  
}
