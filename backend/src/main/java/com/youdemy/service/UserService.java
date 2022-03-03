package com.youdemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.repository.CourseRepository;
import com.youdemy.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public Optional<User> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public void save(User user) {
		repository.save(user);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
