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

        // 게시물마다 조회수를 가져온다.
        for (int i = 0; i < posts.size(); i++) {
            Post tmp = postService.stats(posts.get(i));
            posts.set(i, tmp);
        }

        model.addAttribute("posts", posts);
        return "/main/main";
    }
}
