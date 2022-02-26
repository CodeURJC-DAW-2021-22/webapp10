package com.youdemy;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;

@Service
public class VideoService {

	@Autowired
	private VideoRepository repository;

	public Optional<Video> findById(int id) {
		return repository.findById((int) id);
	}
		
	public boolean exist(int id) {
		return repository.existsById((int) id);
	}

	public List findAll() {
		return (List) repository.findAll();
	}

	public void save(Video video) {
		repository.save(video);
	}

	public void delete(int id) {
		repository.deleteById((int) id);
	}
}
