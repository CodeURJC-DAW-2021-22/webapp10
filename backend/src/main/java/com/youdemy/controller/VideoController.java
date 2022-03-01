package com.youdemy.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.youdemy.model.Course;
import com.youdemy.repository.CourseRepository;
import com.youdemy.service.CourseService;

import com.youdemy.model.Video;
import com.youdemy.repository.VideoRepository;
import com.youdemy.service.VideoService;

@Controller
public class VideoController {
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/video/{id}")
	public String showVideo(Model model, @PathVariable long id) {

		Optional<Video> video = videoService.findById(id);
		if (video.isPresent()) {
			model.addAttribute("video", video.get());
			return "product";
		} else {
			return "itemList";
		}

	}
	
	@GetMapping("/newvideo")
	public String newVideo(Model model) {

		model.addAttribute("availableCourses", courseService.findAll());

		return "newVideo";
	}
	
	@PostMapping("/newvideo")
	public String createVideo(Model model, @RequestParam String title , @RequestParam MultipartFile imageField, @RequestParam String author, @RequestParam String description, @RequestParam int duration, @RequestParam Course course) throws IOException {
		
		Video video = new Video(title, description);
		
		video.setAuthor(author);
		video.setDuration(duration);
		video.setCourse(course);
		
		if (!imageField.isEmpty()) {
			video.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			video.setImage(true);
		}
		
		videoRepository.save(video);
		
		//return "newVideo";
		
		
		return "redirect:/video/"+video.getId();
	}
	
	@GetMapping("/removevideo/{id}")
	public String removeVideo(Model model, @PathVariable long id) {

		Optional<Video> video = videoService.findById(id);
		if (video.isPresent()) {
			videoService.delete(id);
			model.addAttribute("video", video.get());
		}
		return "removedvideo";
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
	public ResponseEntity<Optional<Video>> showVideos(Model model, @PathVariable long id) {

		Optional<Video> dbVideo = Optional.ofNullable(videoRepository.findById(id).get());

		if (dbVideo.isPresent()) {
			
			return ResponseEntity.ok(dbVideo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/videos/{id}")
	public ResponseEntity <Video> deleteVideo (@PathVariable long id){
		Optional<Video> dbVideo = Optional.ofNullable(videoRepository.findById(id).get());
		
		if (dbVideo.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.ok(dbVideo.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/videos/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Video> video = videoService.findById(id);
		if (video.isPresent() && video.get().getImageFile() != null) {

			Resource file = new InputStreamResource(video.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(video.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
}