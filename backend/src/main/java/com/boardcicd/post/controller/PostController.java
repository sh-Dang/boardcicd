package com.boardcicd.post.controller;

import com.boardcicd.post.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 게시판과 관련된 요청들을 모아 전달할 컨트롤러
 *
 * @author 이세형
 * @since 2026/03/24
 * */
@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostController {

    // DB내 게시글들을 불러오는 메서드
    @GetMapping
    public List<Post> getPosts() {
        return null;
    }
}
