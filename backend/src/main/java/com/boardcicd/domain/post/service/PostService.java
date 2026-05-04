package com.boardcicd.domain.post.service;

import com.boardcicd.domain.member.entity.Member;
import com.boardcicd.domain.member.repository.MemberRepository;
import com.boardcicd.domain.post.entity.Post;
import com.boardcicd.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 모든 게시글 불러오는 메서드
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    // 특정 게시글을 불러오는 메서드
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
    }

    // 게시글 등록 메서드
    public Post createPost(Post post) {
        Long memberId = (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        post.setMember(member);

        return postRepository.save(post);
    }

    // 게시글 수정 메서드
    public Post updatePost(Long postId, Post post) throws AccessDeniedException {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        Long memberId = (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        // 작성자 본인 확인
        if (!findPost.getMember().getId().equals(memberId)) {
            throw new AccessDeniedException("게시글 수정 권한이 없습니다.");
        }

        if (post.getTitle() != null) { // 제목에 대한 예외처리
            findPost.setTitle(post.getTitle());
        }

        if (post.getContent() != null) { // 내용에 대한 예외처리
            findPost.setContent(post.getContent());
        }
        return postRepository.save(findPost);
    }

    // 게시글 삭제 메서드
    public void deletePost(Long postId) throws AccessDeniedException {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        // 작성자 본인 확인
        Long memberId = (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (!findPost.getMember().getId().equals(memberId)) {
            throw new AccessDeniedException("게시글 삭제 권한이 없습니다.");
        }
        postRepository.delete(findPost);
    }
}
