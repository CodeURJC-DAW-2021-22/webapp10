package com.youdemy.service;

import java.util.List;
import java.util.Optional;

import com.youdemy.model.Lesson;
import com.youdemy.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LessonService {
	
	@Autowired
	private LessonRepository lessonRepository;
	
	public Optional<Lesson> findById(long id) {
		return lessonRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return lessonRepository.existsById(id);
	}

	public List<Lesson> findAll() {
		return lessonRepository.findAll();
	}

	public void save(Lesson lesson) {
		lessonRepository.save(lesson);
	}

	public void delete(long id) {
		lessonRepository.deleteById(id);
	}

}
