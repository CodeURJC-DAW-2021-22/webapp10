package com.youdemy.service;

import java.util.ArrayList;

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
import com.youdemy.repository.OrderPRepository;


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
	public void init() {
//		Course sql = new Course("SQL", "Curso  SQL", "Emiliano", "https://cdn-icons-png.flaticon.com/512/3161/3161115.png",100, "tecnologia", new ArrayList<>());
//		Course js = new Course("JavaScript", "Curso  JavaScript", "Jose", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Unofficial_JavaScript_logo_2.svg/2048px-Unofficial_JavaScript_logo_2.svg.png",100, "tecnologia", new ArrayList<>());
//		Course react = new Course("React", "Curso  JavaScript", "Pepe", "https://cdn.freebiesupply.com/logos/large/2x/react-1-logo-png-transparent.png",100, "tecnologia", new ArrayList<>());
//		courseService.save(js);
//		courseService.save(react);
//
//		Lesson sql1 = new Lesson("Sql 1", "Primera clase", sql.getAuthor(), "https://storage.cloud.softline.ru/public/images/market_setting/logotype/53445/SQL1.png", 15, null, thumbnail);
//
//		lessonService.save(sql1);
//		sql.addLesson(sql1);
//		Lesson sql2 = new Lesson("Sql 2", "Segunda clase", sql.getAuthor(), "https://storage.cloud.softline.ru/public/images/market_setting/logotype/53445/SQL1.png", 15, null, thumbnail);
//		lessonService.save(sql2);
//		sql.addLesson(sql2);
//
//		courseService.save(sql);

		// Sample users
		User user1 = new User("user", "Ramirez","user@mail.com", passwordEncoder.encode("pass"), "USER");
		User user2 = new User("admin", "Ramirez","admin@mail.com", passwordEncoder.encode("adminpass"), "USER", "ADMIN");
		User user3 = new User("teacher", "Ramirez","teacher@mail.com", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);

		// Sample course
		for (int i = 0; i < 30; i++) {
			byte[] thumbnail = new byte[0];
			Course course = new Course("Java", "Curso de Java", 100, thumbnail,new ArrayList<String>(), new ArrayList<Lesson>(), user1);
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

}
