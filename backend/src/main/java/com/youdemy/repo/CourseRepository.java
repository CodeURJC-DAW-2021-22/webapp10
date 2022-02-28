package com.youdemy.repo;

import com.youdemy.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends  JpaRepository<Course, Integer>{
	

}