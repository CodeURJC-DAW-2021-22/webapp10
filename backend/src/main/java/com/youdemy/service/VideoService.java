package com.youdemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youdemy.model.Course;
import com.youdemy.model.Video;
import com.youdemy.repository.CourseRepository;
import com.youdemy.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	private VideoRepository repository;
	
	public Optional<Video> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Video> findAll() {
		return repository.findAll();
	}

	public void save(Video video) {
		repository.save(video);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
