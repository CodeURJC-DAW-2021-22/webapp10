package com.youdemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontEndController {
	@GetMapping({"/new/**/{path:[^\\.]*}", "/{path:new[^\\.]*}"})
	public String redirect() {
		return "forward:/frontend/index.html";
	}
}
