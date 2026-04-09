package com.boardcicd.domain.post.controller;

import com.boardcicd.domain.post.dto.PostResponseDto;
import com.boardcicd.domain.post.entity.Post;
import com.boardcicd.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    private final PostService postService;// service 주입

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
    public Post getPost(@PathVariable Long postId) {
        log.debug("조회 요청된 게시글 postId={}", postId);
        return postService.getPost(postId);
    }

    // 게시글 등록 메서드
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        log.debug("등록 요청된 게시글 제목 title={}", post.getTitle());
        return postService.createPost(post);
    }

    // 게시글 일부 수정 메서드
    @PatchMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId,
                           @RequestBody Post post) {
        log.debug("수정 요청된 게시글 postId={}", postId);
        return postService.updatePost(postId, post);
    }

    // 게시글 삭제 메서드
    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId) {
        log.debug("삭제 요청된 게시글 postId={}", postId);
        postService.deletePost(postId);
        return "게시글이 삭제되었습니다.";
    }
}
