package com.youdemy.service;

import java.io.IOException;
import java.net.URISyntaxException;

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
import com.youdemy.repository.UserRepository;


@Service
public class DatabaseInitializer {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private VideoService videoService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws IOException, URISyntaxException {
		
		Course sql = new Course("SQL", "Curso  SQL", "Emiliano", "https://cdn-icons-png.flaticon.com/512/3161/3161115.png",100, "tecnologia");
		Course js = new Course("JavaScript", "Curso  JavaScript", "Jose", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Unofficial_JavaScript_logo_2.svg/2048px-Unofficial_JavaScript_logo_2.svg.png",100, "tecnologia");
		Course react = new Course("React", "Curso  JavaScript", "Pepe", "https://cdn.freebiesupply.com/logos/large/2x/react-1-logo-png-transparent.png",100, "tecnologia");
		courseService.save(js);
		courseService.save(react);
		Video sql1 = new Video("Sql 1", "Primera clase", sql.getAuthor(), "https://storage.cloud.softline.ru/public/images/market_setting/logotype/53445/SQL1.png", 15);

		videoService.save(sql1);
		sql.addVideo(sql1);
		Video sql2 = new Video("Sql 2", "Segunda clase", sql.getAuthor(), "https://storage.cloud.softline.ru/public/images/market_setting/logotype/53445/SQL1.png", 15);
		videoService.save(sql2);
		sql.addVideo(sql2);
		
		courseService.save(sql);

		// Sample users

		userRepository.save(new User("user", passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
	}

}
