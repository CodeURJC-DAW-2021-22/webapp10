package com.youdemy.controller;

import com.youdemy.service.UserService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public class BasicAttributes {

    public static void addAttributes(Model model, HttpServletRequest request, UserService userService) {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            model.addAttribute("teacher", request.isUserInRole("TEACHER"));
            model.addAttribute("user", request.isUserInRole("USER"));
            model.addAttribute("isTeacherOrAdmin", (request.isUserInRole("ADMIN") || request.isUserInRole("TEACHER")));
            model.addAttribute("userId", userService.findByFirstName(principal.getName()).getId());

        } else {
            model.addAttribute("logged", false);
        }
    }

}
