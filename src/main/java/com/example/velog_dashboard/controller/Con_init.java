package com.example.velog_dashboard.controller;

import com.example.velog_dashboard.entity.Post;
import com.example.velog_dashboard.service.PostService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class Con_init {

    @Autowired
    PostService postService;

    @GetMapping("")
    public String loadInitPage(Model model) throws ParseException {
        List<Post> posts = postService.posts();
        model.addAttribute("posts", posts);
        return "/main/main";
    }
}
