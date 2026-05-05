package com.boardcicd.domain.post.controller;

import com.boardcicd.domain.post.dto.PostRequestDto;
import com.boardcicd.domain.post.dto.PostResponseDto;
import com.boardcicd.domain.post.entity.Post;
import com.boardcicd.domain.post.service.PostService;
import com.boardcicd.global.dto.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * 게시글(Post) 관련 HTTP 요청을 처리하는 컨트롤러
 *
 * @author 이세형
 * @since 2026/03/24
 * */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService; // service 주입
    private final ObjectMapper objectMapper; // 기본 생성되는 Bean을 주입받아 사용하기(직접구현X)

    // DB내 게시글들을 불러오는 메서드
    @GetMapping
    public List<PostResponseDto> getPosts() {
        log.debug("전체 게시글 조회요청");
        return postService.getPosts().stream()
                .map(PostResponseDto::new)
                .toList();
    }

    // 특정 게시글 조회 메서드
    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDto> getPost(@PathVariable Long postId) throws JsonProcessingException {
        log.debug("조회 요청된 게시글 postId={}", postId);

        /*
          Controller Layer에서 DTO 변환을 수행하는 이유는
          MVC 패턴에서 Controller가 HTTP 요청/응답을 담당하는 계층이기 때문이다.

          DTO는 클라이언트와의 데이터 교환을 위한 객체로, 프레젠테이션 계층에 속한다.
          따라서 비즈니스 로직을 담당하는 Service가 아닌,
          Controller에서 Entity → ResponseDTO 변환을 수행하는 것이 계층 분리에 적합하다.
         */

        /*
        * 응답 JSON의 정확한 형식을 보기 위한 코드 수정, Spring 내부의 ObjectMapper를 사용
        * */
        Post foundPost = postService.getPost(postId);

        PostResponseDto dto = new PostResponseDto(foundPost);
        ApiResponse<PostResponseDto> response = ApiResponse.success(dto);

        log.debug("response = {}", objectMapper.writeValueAsString(response));

        return response;
    }

    // 게시글 등록 메서드
    @PostMapping
    public ApiResponse<PostResponseDto> createPost(@RequestBody PostRequestDto.Create postRequestDto) {
        log.debug("등록 요청된 게시글 제목 title={}", postRequestDto.getTitle());
        log.debug("등록 요청된 게시글 내용 content={}", postRequestDto.getContent());

        Post savedPost = postService.createPost(postRequestDto); // ResponseEntity를 반환하기
        return ApiResponse.success(new PostResponseDto(savedPost));
    }


    // 게시글 일부 수정 메서드
    @PatchMapping("/{postId}")
    public ApiResponse<PostResponseDto> updatePost(@PathVariable Long postId,
                           @RequestBody PostRequestDto.Update postRequestDto) throws AccessDeniedException {
        log.debug("수정 요청된 게시글 postId={}", postId);

        Post updatedPost = postService.updatePost(postId, postRequestDto);
        return ApiResponse.success(new PostResponseDto(updatedPost));
    }

    /**
     * 게시글 삭제 API
     * <p>
     * [응답]
     * - 상태코드: 200 OK
     * - Body:
     * {
     *   "success": true,
     *   "message": "삭제 완료",
     *   "data": null
     * }
     *
     * @since 2026/04/11
     */
    @DeleteMapping("/{postId}")
    public ApiResponse<Object> deletePost(@PathVariable Long postId) throws AccessDeniedException {
        log.debug("삭제 요청된 게시글 postId={}", postId);

        postService.deletePost(postId);
        return ApiResponse.success(null);
    }

}
