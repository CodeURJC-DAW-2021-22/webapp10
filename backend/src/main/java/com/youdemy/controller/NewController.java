
package com.youdemy.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class NewController {
	@GetMapping({"/new/**/{path:[^\\.]*}", "/{path:new[^\\.]*}"})
	public String redirect() {
		return "forward:/new/index.html";
	}
}
