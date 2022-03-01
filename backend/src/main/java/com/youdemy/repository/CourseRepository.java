package com.youdemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youdemy.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	

}