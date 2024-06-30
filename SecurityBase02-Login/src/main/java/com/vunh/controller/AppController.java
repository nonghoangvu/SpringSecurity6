package com.vunh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping
    public String index() {
        return "index";
    }
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("user","Login success");
        return "index";
    }

}
