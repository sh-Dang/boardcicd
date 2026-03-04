package com.board.controller;

import com.board.dto.request.PostRequest;
import com.board.dto.response.ApiResponse;
import com.board.dto.response.PostResponse;
import com.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PostResponse.Summary>>> getPosts(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(postService.getPosts(keyword, pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse.Detail>> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(postService.getPost(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse.Detail>> createPost(
            @Valid @RequestBody PostRequest.Create request,
            @AuthenticationPrincipal UserDetails userDetails) {
        PostResponse.Detail response = postService.createPost(request, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("게시글이 작성되었습니다.", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse.Detail>> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest.Update request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(postService.updatePost(id, request, userDetails.getUsername())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(id, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시글이 삭제되었습니다.", null));
    }
}
