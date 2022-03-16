package com.youdemy.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		User guest = new User("guest", "Guest","gues@mail.com", passwordEncoder.encode("pass2"), "USER");
		User user1 = new User("user", "Ramirez","user@mail.com", passwordEncoder.encode("pass"), "USER");
		User user2 = new User("admin", "Ramirez","admin@mail.com", passwordEncoder.encode("adminpass"), "USER", "ADMIN");
		User user3 = new User("teacher", "Ramirez","teacher@mail.com", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");
		User user4 = new User("teacher2", "Ramirez2","teacher2@mail.com", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");
		
		
		userRepository.save(guest);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		
		
		byte[] thumbnail = loadRandomImage();
		
		ArrayList<String> tags = new ArrayList<>();
		tags.add("Tag1");
		tags.add("Tag2");
		
		
		Course course1 = new Course("Java", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user4);
		
		List<Lesson> lessons1 = new ArrayList<>();
		
		Lesson lesson1 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course1, "", 1);
		
		lessons1.add(lesson1);
		
		course1.setLessons(lessons1);
		

		courseService.save(course1);
		
		

		Course course = new Course("Python", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
		
		List<Lesson> lessons = new ArrayList<>();
		
		Lesson lesson = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course, "", 1);
		
		lessons.add(lesson);
		
		course.setLessons(lessons);
		

		courseService.save(course);
		
		Course course2 = new Course("SQL", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
		
		List<Lesson> lessons2 = new ArrayList<>();
		
		Lesson lesson2 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course2, "", 1);
		
		lessons2.add(lesson2);
		
		course2.setLessons(lessons2);
		

		courseService.save(course2);
		
		Course course3 = new Course("C#", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
		
		List<Lesson> lessons3 = new ArrayList<>();
		
		Lesson lesson3 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course3, "", 1);
		
		lessons3.add(lesson3);
		
		course3.setLessons(lessons3);
		

		courseService.save(course3);
		
		Course course4 = new Course("Ensamblador", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
		
		List<Lesson> lessons4 = new ArrayList<>();
		
		Lesson lesson4 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course4, "", 1);
		
		lessons4.add(lesson4);
		
		course4.setLessons(lessons4);
		
		courseService.save(course4);
		
		
	Course course5 = new Course("Ensamblador", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
		
		List<Lesson> lessons5 = new ArrayList<>();
		
		Lesson lesson5 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course5, "", 1);
		
		lessons5.add(lesson5);
		
		course5.setLessons(lessons5);
		
		courseService.save(course5);
		
		
		
		
		Course course6 = new Course("Relacional;", "Curso de Relacionl", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
		
		List<Lesson> lessons6 = new ArrayList<>();
		
		Lesson lesson6 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course6, "", 1);
		
		lessons6.add(lesson6);
		
		course6.setLessons(lessons6);
		
		courseService.save(course6);
		

		
		
//		// Sample course
//		for (int i = 0; i < 0; i++) {
//			byte[] thumbnail = loadRandomImage();
//
//			ArrayList<String> tags = new ArrayList<>();
//			tags.add("Tag1");
//			tags.add("Tag2");
//
//			Course course = new Course("Java", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user1);
//
//			List<Lesson> lessons = new ArrayList<>();
//
//			for (int j = 0; j < 5; j++) {
//				lessons.add(new Lesson(j + "", "Desc", userRepository.getById(Long.parseLong("3")), course, "https://www.youtube.com/embed/HDhR2Yhnvfo", 1));
//			}
//
//			course.setLessons(lessons);
//
//			courseService.save(course);
//		}	
//		
			
//		// Sample orders
		OrderP order1 = new OrderP(user1.getId(),10,course1.getId(),user1.getFirstName(),course1.getTitle(),"payment Method","billing Address","Country","Region", "data Card");
		orderRepository.save(order1);

		OrderP order2 = new OrderP(user2.getId(),10,course2.getId(),user2.getFirstName(),course2.getTitle(),"payment Method","billing Address","Country","Region", "data Card");
		orderRepository.save(order2);

		OrderP order3 = new OrderP(user3.getId(),10,course3.getId(),user3.getFirstName(),course3.getTitle(),"payment Method","billing Address","Country","Region", "data Card");
		orderRepository.save(order3);

		OrderP order4 = new OrderP(user4.getId(),10,course1.getId(),user4.getFirstName(),course1.getTitle(),"payment Method","billing Address","Country","Region", "data Card");
		orderRepository.save(order4);
							
		OrderP order5 = new OrderP(user1.getId(),10,course5.getId(),user1.getFirstName(),course5.getTitle(),"payment Method","billing Address","Country","Region", "data Card");
		orderRepository.save(order5);

		OrderP order6 = new OrderP(user2.getId(),10,course6.getId(),user2.getFirstName(),course6.getTitle(),"payment Method","billing Address","Country","Region", "data Card");
		orderRepository.save(order6);
	}

	public byte[] loadRandomImage() throws IOException {
		int randomImgNum = (int) Math.floor(Math.random() * 9) + 1;
		File image = ResourceUtils.getFile("classpath:./fakeImages/" + randomImgNum + ".jpg");

		return Files.readAllBytes(image.toPath());
	}
  
}
