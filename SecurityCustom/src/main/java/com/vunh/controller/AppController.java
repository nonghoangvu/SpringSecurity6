package com.vunh.controller;

import com.vunh.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AppController {
    @ModelAttribute("authenticated")
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("message", "Login with role admin");
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
