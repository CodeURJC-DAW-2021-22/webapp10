package com.youdemy.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youdemy.controller.BasicAttributes;
import com.youdemy.model.Course;
import com.youdemy.model.Lesson;
import com.youdemy.model.User;
import com.youdemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.youdemy.service.CourseService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/courses")
public class RestCourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        BasicAttributes.addAttributes(model, request, userService);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable long id) {
        Optional<Course> course = courseService.findById(id);

        if (course.isPresent()) return course.get();

        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable long id) {
        courseService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page<Course> getCoursesInPage(@RequestParam Optional<Integer> page,
                                         @RequestParam Optional<String> search) {
        System.out.println(page.orElse(0));

        return courseService.findByTitle(search.orElse(""),
                PageRequest.of(page.orElse(0), 6));
    }

    @PostMapping("/new")
    public Course create(@RequestAttribute("title") String title, @RequestAttribute("image") MultipartFile image,
                         @RequestAttribute("description") String description, @RequestAttribute("price") int price,
                         @RequestAttribute("tags") String tags, @RequestAttribute("lessons") String lessons,
                         Model model) throws IOException {
        Course course = new Course();
        User author = userService.findByFirstName(Objects.requireNonNull(model.getAttribute("userName")).toString());

        List<Lesson> lessonList = new ArrayList<>(Arrays.asList(new ObjectMapper().readValue(lessons, Lesson[].class)));
        lessonList.forEach(lesson -> {
            lesson.setAuthor(author);
            lesson.setCourse(course);
        });

        course.setAuthor(author);
        course.setThumbnail(image.getBytes());
        course.setTitle(title);
        course.setDescription(description);
        course.setPrice(price);
        course.setTags(new ArrayList(Arrays.asList(new ObjectMapper().readValue(tags, String[].class))));
        course.setLessons(lessonList);

        courseService.save(course);

        return courseService.findById(course.getId()).get();
    }

}
