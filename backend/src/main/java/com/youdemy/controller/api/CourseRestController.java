package com.youdemy.controller.api;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youdemy.controller.BasicAttributes;
import com.youdemy.model.Lesson;
import com.youdemy.model.OrderP;
import com.youdemy.repository.UserRepository;
import com.youdemy.service.OrderPService;
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
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderPService orderPService;

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
	public ResponseEntity<Course> getCourse(Model model, @PathVariable long id, HttpServletRequest request){
		Optional<Course> op = courseService.findById(id);

		if (op.isPresent()) {
			Course course = op.get();

			Principal principal = request.getUserPrincipal();
			AtomicBoolean hasAccess = new AtomicBoolean(false);
			if(principal != null) {
				String userName = principal.getName();
				Optional<User> user = userRepository.findByFirstName(userName);
				long userId = user.get().getId();

				if(course.getAuthor().getId() == userId || Objects.equals(model.getAttribute("isAdmin"), true)) {
					hasAccess.set(true);
				}

				ArrayList<OrderP> orders = new ArrayList<>(orderPService.findByUserId(userId));

				orders.forEach(order -> {
					Course orderCourse = courseService.findById(order.getCourse()).get();
					if(orderCourse.getId() == course.getId())
						hasAccess.set(true);
				});
			}

			// Empty lesson video urls if user doesn't have access to course
			if(!hasAccess.get()) {
				course.getLessons().forEach(lesson -> {
					lesson.setVideoUrl("");
				});
			}

			return new ResponseEntity<>(course, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//Create Course
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Course> postNewCourse(@RequestParam("title") String title, @RequestParam("image") MultipartFile image,
												@RequestParam("description") String description, @RequestParam("price") int price,
												@RequestParam("tags") String tags, @RequestParam("lessons") String lessons,
												Model model) throws IOException {
		if(model.getAttribute("logged") == Boolean.valueOf(true) && model.getAttribute("isTeacherOrAdmin") == Boolean.valueOf(true)) {
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
			
			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
			return ResponseEntity.created(location).body(course);
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

	@GetMapping("/thumbnail/{id}")
	public @ResponseBody byte[] getThumbnail(Model model, @PathVariable String id) {
		Optional<Course> course = courseService.findById(Long.parseLong(id));
		return course.map(Course::getThumbnail).orElse(null);
	}
	
}
