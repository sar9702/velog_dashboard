package com.example.velog_dashboard.entity;

import lombok.Data;

@Data
public class Post {

    // ID
    private String id;

    // 제목
    private String title;

    // 전체 조회수
    private Integer totalViews;

    // 썸네일
    private String thumbnailURL;

    // 바로가기 링크
    private String url;
}
