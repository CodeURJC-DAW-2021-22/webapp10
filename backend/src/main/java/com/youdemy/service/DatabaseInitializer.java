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
import com.youdemy.model.Order;
import com.youdemy.repository.UserRepository;
import com.youdemy.repository.OrderRepository;


@Service
public class DatabaseInitializer {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private VideoService videoService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		Course sql = new Course("SQL", "Curso  SQL", "Emiliano", "https://cdn-icons-png.flaticon.com/512/3161/3161115.png",100, "tecnologia", new ArrayList<>());
		Course js = new Course("JavaScript", "Curso  JavaScript", "Jose", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Unofficial_JavaScript_logo_2.svg/2048px-Unofficial_JavaScript_logo_2.svg.png",100, "tecnologia", new ArrayList<>());
		Course react = new Course("React", "Curso  JavaScript", "Pepe", "https://cdn.freebiesupply.com/logos/large/2x/react-1-logo-png-transparent.png",100, "tecnologia", new ArrayList<>());
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
		userRepository.save(new User("user", "Ramirez","user@mail.com", passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new User("admin", "Ramirez","user@mail.com", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
		userRepository.save(new User("teacher", "Ramirez","user@mail.com", passwordEncoder.encode("teacherpass"), "USER", "TEACHER"));
	}

}
