package com.example.velog_dashboard.service;

import com.example.velog_dashboard.entity.Post;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void posts() throws ParseException {
        List<Post> posts = postService.posts();
        System.out.println(posts);
    }

    @Test
    void stats() throws ParseException {
        List<Post> posts = postService.posts();
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            post = postService.stats(post);

            System.out.println(post);
        }
    }
}