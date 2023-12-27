package com.example.velog_dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Con_init {

    @GetMapping("")
    public String loadInitPage(Model model) {
        return "/main/main";
    }
}
