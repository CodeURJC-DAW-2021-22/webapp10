package com.youdemy;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class WebController {
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String guardarAnuncio(Model model) {
		Course test = new Course("SQL", "Curso  SQL", "Emiliano", "image.url",100, "tecnologia");
		repository.save(test);
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {		
		return "admin";	
	}
	
	@GetMapping("/newvideo")
	public String newVideo(Model model) {
		return "newVideo";		
	}
	
	@PostMapping("/register")
	public String createUser(Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String role, @RequestParam String userEmail, @RequestParam String userPassword) {
		User user = new User(firstName, lastName, userEmail, userPassword, role);
		userRepository.save(user);
		return "login";		
	}
	
	@GetMapping("/login")
	public String login(Model model) {		
		return "login";		
	}
	
	@GetMapping("/itemList")
	public String itemList(Model model) {			
		return "itemList";		
	}


	@PostMapping("/newvideo")
	public String createVideo(Model model, @RequestParam String title , @RequestParam MultipartFile imageField, @RequestParam String author, @RequestParam String description, @RequestParam int duration, @RequestParam String course) throws IOException {
		
		Video video = new Video(title, description);
		
		video.setAuthor(author);
		video.setDuration(duration);
		video.setCourse(course);
		
		if (!imageField.isEmpty()) {
			video.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			video.setImage(true);
		}
		
		videoRepository.save(video);
		
		return "newVideo";
		
		
		//return "redirect:/video/"+video.getId();
	}
	
	@PostMapping("/editvideo")
	public String editvideoProcess(Model model, Video video, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		updateImage(video, removeImage, imageField);

		videoService.save(video);

		model.addAttribute("videoId", video.getId());

		return "redirect:/videos/"+video.getId();
	}

	private void updateImage(Video video, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			video.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			video.setImage(true);
		} else {
			if (removeImage) {
				video.setImageFile(null);
				video.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Video dbVideo = videoService.findById(video.getId()).orElseThrow();
				if (dbVideo.getImage()) {
					video.setImageFile(BlobProxy.generateProxy(dbVideo.getImageFile().getBinaryStream(),
							dbVideo.getImageFile().length()));
					video.setImage(true);
				}
			}
		}
	}

	@GetMapping("/videos/{id}")
	public ResponseEntity<Optional<Video>> showVideo(Model model, @PathVariable int id) {

		Optional<Video> dbVideo = Optional.ofNullable(videoRepository.findById(id).get());

		if (dbVideo.isPresent()) {
			
			return ResponseEntity.ok(dbVideo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <Video> deleteVideo (@PathVariable int id){
		Optional<Video> dbVideo = Optional.ofNullable(videoRepository.findById(id).get());
		
		if (dbVideo.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.ok(dbVideo.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/cart")
	public String cart(Model model) {		
		return "cart";		
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {		
		return "checkout";		
	}
	
	@GetMapping("/success")
	public String success(Model model) {		
		return "success";		
	}	
}
