package com.boardcicd.domain.post.dto;

import com.boardcicd.domain.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
        private final Long id;
        private final String title;
        private final String content;
        private final String username;
        @JsonFormat(pattern = "yy/MM/dd HH:mm") // 보낼 JSON양식 설정
        private final LocalDateTime createdAt;

        public PostResponseDto(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.username = post.getMember().getUsername();
            this.createdAt = post.getCreatedAt();
        }

}
