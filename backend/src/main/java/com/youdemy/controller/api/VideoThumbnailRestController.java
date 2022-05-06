package com.youdemy.controller.api;

import com.youdemy.controller.BasicAttributes;
import com.youdemy.model.VideoThumbnail;
import com.youdemy.service.UserService;
import com.youdemy.service.VideoThumbnailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@RestController
public class VideoThumbnailRestController {

    @Autowired
    private VideoThumbnailService videoThumbnailService;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        BasicAttributes.addAttributes(model, request, userService);
    }

    @PostMapping(value = "/api/videoThumbnail")
    public Long uploadVideoThumbnail(@RequestParam("image") MultipartFile image) throws IOException {
        VideoThumbnail videoThumbnail = new VideoThumbnail();
        videoThumbnail.setName(image.getName());
        videoThumbnail.setData(image.getBytes());
        videoThumbnail.setType(image.getContentType());

        return videoThumbnailService.save(videoThumbnail);
    }

    @GetMapping(value = "/api/videoThumbnail/{imageId}")
    public @ResponseBody byte[] downloadVideoThumbnail(@PathVariable String imageId) {
        Optional<VideoThumbnail> thumbnail = videoThumbnailService.get(Long.parseLong(imageId));
        return thumbnail.map(VideoThumbnail::getData).orElse(null);
    }

}
