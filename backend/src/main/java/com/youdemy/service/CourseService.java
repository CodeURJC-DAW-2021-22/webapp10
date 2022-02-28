package com.youdemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youdemy.model.Course;
import com.youdemy.repository.CourseRepository;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepository repository;
	
	public Optional<Course> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Course> findAll() {
		return repository.findAll();
	}

	public void save(Course course) {
		repository.save(course);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}