package com.boardcicd.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 게시글 관련 요청들을 받아서 Entity에 넘겨줄 Dto
 * 현재 create, update class를 구분하여 생성하고 있다.
 *
 * @since 2026/05/05
 */
public class PostRequestDto {

    @Getter
    public static class Create {
        @NotBlank(message = "제목을 입력해주세요")
        @Size(max = 200, message = "제목은 200자 이내로 입력해주세요")
        private String title;

        @NotBlank(message = "내용을 입력해주세요")
        private String content;
    }

    @Getter
    public static class Update {
        @NotBlank(message = "제목을 입력해주세요")
        @Size(max = 200, message = "제목은 200자 이내로 입력해주세요")
        private String title;

        @NotBlank(message = "내용을 입력해주세요")
        private String content;
    }
}
