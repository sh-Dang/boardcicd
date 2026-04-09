package com.boardcicd.domain.post.dto;

import com.boardcicd.domain.post.entity.Post;

public class PostResponseDto {
        private Long id;
        private String title;
        private String content;
        private String username;

        public PostResponseDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.username = post.getMember().getUsername();
        }

        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getContent() { return content; }
        public String getUsername() { return username; }
    }
