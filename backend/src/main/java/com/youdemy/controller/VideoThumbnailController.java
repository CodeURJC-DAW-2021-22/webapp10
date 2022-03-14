package com.youdemy.controller;

import com.youdemy.model.Course;
import com.youdemy.model.VideoThumbnail;
import com.youdemy.repository.VideoThumbnailRepository;
import com.youdemy.service.VideoThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class VideoThumbnailController {

    @Autowired
    private VideoThumbnailService videoThumbnailService;

    @PostMapping(value = "/image/new")
    public Long uploadVideoThumbnail(@RequestParam("image") MultipartFile image) throws IOException {
        VideoThumbnail videoThumbnail = new VideoThumbnail();
        videoThumbnail.setName(image.getName());
        videoThumbnail.setData(image.getBytes());
        videoThumbnail.setType(image.getContentType());

        return videoThumbnailService.save(videoThumbnail);
    }

    @GetMapping(value = "/image/{imageId}")
    public @ResponseBody byte[] downloadVideoThumbnail(@PathVariable String imageId) {
        Optional<VideoThumbnail> thumbnail = videoThumbnailService.get(Long.parseLong(imageId));
        return thumbnail.map(VideoThumbnail::getData).orElse(null);
    }

}
