package com.youdemy.repository;

import com.youdemy.model.Course;
import com.youdemy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("select c from Course c where c.title like %:title%")
    Page<Course> findByTitle(String title, Pageable pageable);

    @Query("select c from Course c inner join OrderP o on c.id = o.course where o.user = :userId")
    Page<Course> findByUser(long userId, Pageable pageable);

    Page<Course> findByAuthor(User author, Pageable pageable);

    List<Course> findByAuthor(User author);

}
