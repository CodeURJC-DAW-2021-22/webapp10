package com.youdemy.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
	private VideoThumbnailService videoThumbnailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws IOException {
		// Sample users
//		User guest = new User("guest@mail.com","guest@mail.com", "Guest", passwordEncoder.encode("pass2"), "USER");
		User user1 = new User("user@mail.com", "user@mail.com", "Ramirez", passwordEncoder.encode("pass"), "USER");
		User user2 = new User("admin@mail.com", "admin@mail.com", "Gonzalez", passwordEncoder.encode("adminpass"), "USER", "ADMIN");
		User user3 = new User("teacher@mail.com", "teacher@mail.com", "Fernandez", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");
//		User user4 = new User("teacher2@mail.com","teacher2@mail.com", "Ramirez2", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");


//		userRepository.save(guest);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
//		userRepository.save(user4);


//		byte[] thumbnail = loadRandomImage();
//
//		ArrayList<String> tags = new ArrayList<>();
//		tags.add("Tag1");
//		tags.add("Tag2");
//
//
//		Course course1 = new Course("Java", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user4);
//
//		List<Lesson> lessons1 = new ArrayList<>();
//
//		Lesson lesson1 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course1, "", 1);
//
//		lessons1.add(lesson1);
//
//		course1.setLessons(lessons1);
//
//
//		courseService.save(course1);
//
//
//
//		Course course = new Course("Python", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
//
//		List<Lesson> lessons = new ArrayList<>();
//
//		Lesson lesson = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course, "", 1);
//
//		lessons.add(lesson);
//
//		course.setLessons(lessons);
//
//
//		courseService.save(course);
//
//		Course course2 = new Course("SQL", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
//
//		List<Lesson> lessons2 = new ArrayList<>();
//
//		Lesson lesson2 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course2, "", 1);
//
//		lessons2.add(lesson2);
//
//		course2.setLessons(lessons2);
//
//
//		courseService.save(course2);
//
//		Course course3 = new Course("C#", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
//
//		List<Lesson> lessons3 = new ArrayList<>();
//
//		Lesson lesson3 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course3, "", 1);
//
//		lessons3.add(lesson3);
//
//		course3.setLessons(lessons3);
//
//
//		courseService.save(course3);
//
//		Course course4 = new Course("Ensamblador", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
//
//		List<Lesson> lessons4 = new ArrayList<>();
//
//		Lesson lesson4 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course4, "", 1);
//
//		lessons4.add(lesson4);
//
//		course4.setLessons(lessons4);
//
//		courseService.save(course4);
//
//
//	Course course5 = new Course("Ensamblador", "Curso de Java", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
//
//		List<Lesson> lessons5 = new ArrayList<>();
//
//		Lesson lesson5 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course5, "", 1);
//
//		lessons5.add(lesson5);
//
//		course5.setLessons(lessons5);
//
//		courseService.save(course5);
//
//
//
//
//		Course course6 = new Course("Relacional;", "Curso de Relacionl", 100, thumbnail, tags, new ArrayList<Lesson>(), user3);
//
//		List<Lesson> lessons6 = new ArrayList<>();
//
//		Lesson lesson6 = new Lesson("Lesson 1", "Desc", userRepository.getById(Long.parseLong("3")), course6, "", 1);
//
//		lessons6.add(lesson6);
//
//		course6.setLessons(lessons6);
//
//		courseService.save(course6);
//
//
//		ArrayList<Course> courses = new ArrayList<>();
//
//		// Sample course
//		for (int i = 0; i < 5; i++) {
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
//			for (int j = 0; j < 3; j++) {
//				VideoThumbnail videoThumbnail = new VideoThumbnail();
//				videoThumbnail.setName("" + j);
//				videoThumbnail.setData(loadRandomImage());
//				videoThumbnail.setType("jpg");
//
//				long thumbnailId = videoThumbnailService.save(videoThumbnail);
//				lessons.add(new Lesson("Lesson " + j, "Desc", userRepository.getById(Long.parseLong("3")), course, "https://www.youtube.com/embed/HDhR2Yhnvfo", thumbnailId));
//			}
//
//			course.setLessons(lessons);
//
//
//			courses.add(courseService.save(course));
//		}
//
//
////		// Sample orders
//		OrderP order1 = new OrderP(user1.getId(),10,courses.get(0).getId(),user1.getFirstName(),courses.get(0).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		orderRepository.save(order1);
//
//		OrderP order2 = new OrderP(user2.getId(),10,courses.get(0).getId(),user2.getFirstName(),courses.get(0).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		OrderP order22 = new OrderP(user2.getId(),10,courses.get(1).getId(),user2.getFirstName(),courses.get(1).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		OrderP order23 = new OrderP(user2.getId(),10,courses.get(3).getId(),user2.getFirstName(),courses.get(3).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		OrderP order24 = new OrderP(user2.getId(),10,courses.get(3).getId(),user2.getFirstName(),courses.get(3).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		orderRepository.save(order2);
//		orderRepository.save(order22);
//		orderRepository.save(order23);
//		orderRepository.save(order24);
//
//		OrderP order3 = new OrderP(user3.getId(),10,courses.get(3).getId(),user3.getFirstName(),courses.get(3).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		orderRepository.save(order3);
//
//		OrderP order4 = new OrderP(user4.getId(),10,courses.get(3).getId(),user4.getFirstName(),courses.get(3).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		OrderP order42 = new OrderP(user4.getId(),10,courses.get(3).getId(),user4.getFirstName(),courses.get(3).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		orderRepository.save(order4);
//		orderRepository.save(order42);
//
//		OrderP order5 = new OrderP(user1.getId(),10,courses.get(3).getId(),user1.getFirstName(),courses.get(3).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		orderRepository.save(order5);
//
//		OrderP order6 = new OrderP(user2.getId(),10,courses.get(3).getId(),user2.getFirstName(),courses.get(3).getTitle(),"payment Method","billing Address","Country","Region", "data Card");
//		orderRepository.save(order6);
//	}
//
//	public byte[] loadRandomImage() throws IOException {
//		URL url = new URL("https://picsum.photos/1920/1080");
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		InputStream is = null;
//		try {
//			is = url.openStream ();
//			byte[] byteChunk = new byte[4096];
//			int n;
//
//			while ( (n = is.read(byteChunk)) > 0 ) {
//				baos.write(byteChunk, 0, n);
//			}
//
//			return baos.toByteArray();
//		}
//		catch (IOException e) {
//			e.printStackTrace ();
//		}
//		finally {
//			if (is != null) { is.close(); }
//		}
//
//		return null;
//	}
	}
}
