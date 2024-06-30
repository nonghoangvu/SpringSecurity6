package com.vunh.securitybase01.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("msg", "Hello World!");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getPrincipal();
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("msg", "Hello I'm Admin!");
        return "index";
    }
}
