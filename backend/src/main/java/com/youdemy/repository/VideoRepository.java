package com.youdemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youdemy.model.Video;

public interface VideoRepository extends  JpaRepository<Video, Long>{
	

}