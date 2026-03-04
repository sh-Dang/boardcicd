package com.board.dto.response;

import com.board.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class PostResponse {

    @Getter
    @Builder
    public static class Summary {
        private Long id;
        private String title;
        private String authorNickname;
        private int viewCount;
        private LocalDateTime createdAt;

        public static Summary from(Post post) {
            return Summary.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .authorNickname(post.getMember().getNickname())
                    .viewCount(post.getViewCount())
                    .createdAt(post.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Detail {
        private Long id;
        private String title;
        private String content;
        private String authorNickname;
        private Long authorId;
        private int viewCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static Detail from(Post post) {
            return Detail.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .authorNickname(post.getMember().getNickname())
                    .authorId(post.getMember().getId())
                    .viewCount(post.getViewCount())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .build();
        }
    }
}
