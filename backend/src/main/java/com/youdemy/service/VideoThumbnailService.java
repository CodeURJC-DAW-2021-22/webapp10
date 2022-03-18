package com.youdemy.service;

import com.youdemy.model.VideoThumbnail;
import com.youdemy.repository.LessonRepository;
import com.youdemy.repository.VideoThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoThumbnailService {

    @Autowired
    private VideoThumbnailRepository videoThumbnailRepository;

    public long save(VideoThumbnail thumbnail) {
        return videoThumbnailRepository.save(thumbnail).getId();
    }

    public Optional<VideoThumbnail> get(long id) {
        return videoThumbnailRepository.findById(id);
    }
}
