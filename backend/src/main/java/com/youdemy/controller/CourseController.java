package com.youdemy.controller;

import com.youdemy.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value = {
            "",
            "/"
    })
    public ModelAndView courseList(@RequestParam(required = false) String search, Map<String, Object> model) {
        model.put("search", search);

        return new ModelAndView("courseList", model);
    }

    @GetMapping("/new")
    public ModelAndView newCourse() {
        ModelAndView view = new ModelAndView("newCourse");

        return view;
    }

}
