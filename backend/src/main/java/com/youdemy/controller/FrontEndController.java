package com.youdemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontEndController {
    @GetMapping("/new/**/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/new/index.html";
    }
}
