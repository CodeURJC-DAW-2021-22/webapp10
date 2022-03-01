package com.youdemy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
	
	@RequestMapping("/signin")
	public String signIn() {
		return "signin";
	}

	@RequestMapping("/singinError")
	public String signinError() {
		return "signinError";
	}

	@RequestMapping("/signup")
	public String signUp() {
		return "signup";
	}

	@RequestMapping("/signupError")
	public String signupError() {
		return "signupError";
	}

	@RequestMapping("/signout")
	public String signOut() {
		return "signout";
	}

	@RequestMapping("/signoutError")
	public String signoutError() {
		return "signoutError";
	}

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/userError")
	public String userError() {
		return "userError";
	}

}
