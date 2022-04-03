package com.youdemy.service;

import com.youdemy.model.Course;
import com.youdemy.model.CourseBoughtTimes;
import com.youdemy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseBoughtTimesService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrderPService orderPService;

    public List<CourseBoughtTimes> getCourseBoughtTimes(User user) {
        ArrayList<Course> teacherCourses = (ArrayList<Course>) courseService.findByAuthor(user);
        ArrayList<CourseBoughtTimes> coursesBoughtTimes = new ArrayList<>();

        teacherCourses.forEach(course -> {
            CourseBoughtTimes courseBoughtTimes = new CourseBoughtTimes(course.getTitle(), orderPService.countByCourse(course.getId()));

            coursesBoughtTimes.add(courseBoughtTimes);
        });

        return coursesBoughtTimes;
    }

}
