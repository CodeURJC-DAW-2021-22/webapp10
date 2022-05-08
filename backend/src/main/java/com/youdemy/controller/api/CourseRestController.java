package com.youdemy.controller.api;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.youdemy.controller.BasicAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.youdemy.model.Course;
import com.youdemy.model.User;
import com.youdemy.service.CourseService;
import com.youdemy.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	UserService userService;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		BasicAttributes.addAttributes(model, request, userService);
	}
	
	
	//Get Courses
	@GetMapping("/")
	public Collection<Course> getAllCourses() {
		return courseService.findAll();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page<Course> getCoursesInPage(@RequestParam Optional<Integer> page,
										 @RequestParam Optional<String> search) {
		System.out.println(page.orElse(0));

		return courseService.findByTitle(search.orElse(""),
				PageRequest.of(page.orElse(0), 6));
	}

	//Get Specific Course
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable long id){
		Optional<Course> op = courseService.findById(id);

		if (op.isPresent()) {
			Course course = op.get();
			return new ResponseEntity<>(course, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//Create Course
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Course> postNewCourse(@RequestBody Course newCourse, Model model, HttpServletRequest request) throws IOException {
		if(model.getAttribute("logged") == Boolean.valueOf(true) && model.getAttribute("isTeacherOrAdmin") == Boolean.valueOf(true)) {
			User author = userService.findByFirstName(Objects.requireNonNull(model.getAttribute("userName")).toString());
			
			newCourse.getLessons().forEach(lesson -> {
				lesson.setAuthor(author);
				lesson.setCourse(newCourse);
			});
		
			newCourse.setAuthor(author);
			newCourse.setThumbnail(newCourse.getThumbnail());

			courseService.save(newCourse);
			
			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newCourse.getId()).toUri();
			return ResponseEntity.created(location).body(newCourse);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PutMapping("")
	public ResponseEntity<Course> editCourse(@RequestBody Course newCourse) throws IOException {
		courseService.save(newCourse);
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(newCourse.getId()).toUri();

		return ResponseEntity.created(location).body(newCourse);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable long id) {

		try {
			courseService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
}
