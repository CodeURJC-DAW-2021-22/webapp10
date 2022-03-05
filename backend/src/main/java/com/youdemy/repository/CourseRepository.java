package com.youdemy.repository;

import com.youdemy.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("select c from Course c where c.title like %:title%")
    public Page<Course> findByTitle(String title, Pageable pageable);

}
