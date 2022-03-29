package com.youdemy.service;

import java.util.List;
import java.util.Optional;

import com.youdemy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return (List<Course>) repository.findAll();
	}

	public Page<Course> findByTitle(String title, Pageable pageable) {
		return repository.findByTitle(title, pageable);
	}

	public Page<Course> findByAuthor(User author, Pageable pageable) {
		return repository.findByAuthor(author, pageable);
	}

	public List<Course> findByAuthor(User author) {
		return repository.findByAuthor(author);
	}

	public Page<Course> findByUser(long userId, Pageable pageable) {
		return repository.findByUser(userId, pageable);
	}

	public Course save(Course course) {
		return repository.save(course);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
