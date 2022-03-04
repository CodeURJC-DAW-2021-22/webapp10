package com.youdemy.controller;

import com.youdemy.model.VideoThumbnail;
import com.youdemy.repository.VideoThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class VideoThumbnailController {

    @Autowired
    private VideoThumbnailRepository videoThumbnailRepository;

    @PostMapping(value = "/image/new")
    Long uploadVideoThumbnail(@RequestParam("image") MultipartFile image) throws IOException {
        VideoThumbnail videoThumbnail = new VideoThumbnail();
        videoThumbnail.setName(image.getName());
        videoThumbnail.setData(image.getBytes());
        videoThumbnail.setType(image.getContentType());

        return videoThumbnailRepository.save(videoThumbnail).getId();
    }

    @GetMapping(value = "/image/{imageId}")
    Resource downloadVideoThumbnail(@PathVariable String imageId) {
        byte[] data = videoThumbnailRepository.findById(Long.valueOf(imageId))
                .orElseThrow(() -> new IllegalArgumentException("Image not found"))
                .getData();

        return new ByteArrayResource(data);
    }

}
