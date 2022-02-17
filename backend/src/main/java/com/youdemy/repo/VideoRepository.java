package com.youdemy.repo;

import com.youdemy.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends  JpaRepository<Video, Integer> {}
