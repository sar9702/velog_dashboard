package com.example.velog_dashboard.service;

import com.example.velog_dashboard.entity.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    // application.yml 파일에서 값을 가져온다.
    @Value("${velog.username}")
    private String velogUserName;

    @Value("${velog.url}")
    private String velogUrl;

    /**
     * Velog에 등록된 게시물 리스트를 반환하는 함수
     */
    public List<Post> posts() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();

        // Header Setting
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");

        String query = String.format("""
                {
                    "operationName": "Posts",
                    "variables": {
                        "username": "%s",
                        "tag": null
                    },
                    "query": "query Posts($cursor: ID, $username: String, $temp_only: Boolean, $tag: String, $limit: Int) {
                        posts(cursor: $cursor, username: $username, temp_only: $temp_only, tag: $tag, limit: $limit) {
                            id
                            title
                            short_description
                            thumbnail
                            user {
                                id
                                username
                            }
                        }
                    }"
                }
                """, velogUserName);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(query);

        HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject, httpHeaders);

        // Request
        String response = restTemplate.postForObject(velogUrl, entity, String.class);

        // 데이터 정리
        JSONObject jsonResponse = (JSONObject) jsonParser.parse(response);
        JSONObject data = (JSONObject) jsonResponse.get("data");
        JSONArray posts = (JSONArray) data.get("posts");

        List<Post> list = new ArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            JSONObject jsonPost = (JSONObject) posts.get(i);
            String id = (String) jsonPost.get("id");
            String title = (String) jsonPost.get("title");
            String thumbnailURL = (String) jsonPost.get("thumbnail");

            Post post = new Post();
            post.setId(id);
            post.setTitle(title);
            post.setThumbnailURL(thumbnailURL);

            list.add(post);
        }

        return list;
    }
}
