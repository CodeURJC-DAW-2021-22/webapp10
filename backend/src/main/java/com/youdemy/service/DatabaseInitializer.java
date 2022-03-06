package com.youdemy.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.model.Video;
import com.youdemy.model.OrderP;
import com.youdemy.repository.UserRepository;
import com.youdemy.repository.OrderPRepository;


@Service
public class DatabaseInitializer {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private OrderPService orderService;
	
	@Autowired
	private VideoService videoService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderPRepository orderRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		Course sql = new Course("SQL", "Curso  SQL", "Emiliano", "https://cdn-icons-png.flaticon.com/512/3161/3161115.png",100, "tecnologia");
		Course js = new Course("JavaScript", "Curso  JavaScript", "Jose", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Unofficial_JavaScript_logo_2.svg/2048px-Unofficial_JavaScript_logo_2.svg.png",100, "tecnologia");
		Course react = new Course("React", "Curso  JavaScript", "Pepe", "https://cdn.freebiesupply.com/logos/large/2x/react-1-logo-png-transparent.png",100, "tecnologia");
		courseService.save(js);
		courseService.save(react);
		
		Video sql1 = new Video("Sql 1", "Primera clase", sql.getAuthor(), "https://storage.cloud.softline.ru/public/images/market_setting/logotype/53445/SQL1.png", 15, null);

		videoService.save(sql1);
		sql.addVideo(sql1);
		Video sql2 = new Video("Sql 2", "Segunda clase", sql.getAuthor(), "https://storage.cloud.softline.ru/public/images/market_setting/logotype/53445/SQL1.png", 15, null);
		videoService.save(sql2);
		sql.addVideo(sql2);
		
		courseService.save(sql);

		// Sample users
		User user1 = new User("user", "Ramirez","user@mail.com", passwordEncoder.encode("pass"), "USER");
		User user2 = new User("admin", "Ramirez","user@mail.com", passwordEncoder.encode("adminpass"), "USER", "ADMIN");
		User user3 = new User("teacher", "Ramirez","user@mail.com", passwordEncoder.encode("teacherpass"), "USER", "TEACHER");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
			
		// Sample orders
		OrderP order1 = new OrderP(user1.getId(),10,sql.getId());
		orderRepository.save(order1);
		
		OrderP order2 = new OrderP(user2.getId(),20,js.getId());
		orderRepository.save(order2);
		
		OrderP order3 = new OrderP(user3.getId(),30,react.getId());
		orderRepository.save(order3);
		
		OrderP order4 = new OrderP(user1.getId(),40,react.getId());
		orderRepository.save(order4);
		
		OrderP order5 = new OrderP(user2.getId(),50,js.getId());
		orderRepository.save(order5);
		
		OrderP order6 = new OrderP(user3.getId(),60,sql.getId());
		orderRepository.save(order6);
	}
}
