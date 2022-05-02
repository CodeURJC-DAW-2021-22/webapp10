package com.youdemy.controller.api;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.youdemy.model.Course;
import com.youdemy.model.CourseBoughtTimes;
import com.youdemy.service.CourseBoughtTimesService;
import com.youdemy.service.CourseService;
import com.youdemy.service.OrderPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;
import com.youdemy.service.UserService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseBoughtTimesService courseBoughtTimesService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if(principal != null) {
			return ResponseEntity.ok(userRepository.findByFirstName(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	//get all users
	@GetMapping("")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam int page) {
		return ResponseEntity.ok(userService.getUsers(page));
	}

	//get user by id
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) {

		if (userService.exist(id)) {
			User user = userService.findById(id).get();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	//Register new user
	@PostMapping("")
	public ResponseEntity<User> registerNewUser(@RequestBody User user) {
		if(user.getName().isBlank() || userService.existByEmail(user.getEmail())){
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		} else {
			user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
			userService.save(user);
			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(location).body(user);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {

		if (userService.exist(id)) {
			userService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/{id}/graph")
	public ResponseEntity<List<CourseBoughtTimes>> graph(@PathVariable long id) {
		if (userService.exist(id)) {
			User user = userService.findById(id).get();

			if (user.getRoles().contains("TEACHER")) {
				ArrayList<CourseBoughtTimes> coursesBoughtTimes =
						new ArrayList<>(courseBoughtTimesService.getCourseBoughtTimes(user));
				URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

				return ResponseEntity.created(location).body(coursesBoughtTimes);
			}
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
