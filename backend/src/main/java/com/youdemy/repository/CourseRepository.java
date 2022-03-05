package com.youdemy.repository;

import com.youdemy.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("select c from Course c where c.title like %:title%")
    public List<Course> findByTitle(String title);

}
