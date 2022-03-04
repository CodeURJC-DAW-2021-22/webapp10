package com.youdemy.repository;

import com.youdemy.model.VideoThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoThumbnailRepository extends JpaRepository<VideoThumbnail, Long> {}
