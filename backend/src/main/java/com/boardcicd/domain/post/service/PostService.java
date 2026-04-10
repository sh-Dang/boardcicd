package com.boardcicd.domain.post.service;

import com.boardcicd.domain.member.entity.Member;
import com.boardcicd.domain.member.repository.MemberRepository;
import com.boardcicd.domain.post.entity.Post;
import com.boardcicd.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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

        // 테스트용 임시코드(Member연결후 지우기) 4/10
        Member member = new Member();
        member.setId(3L);
        member.setEmail("hi@naver.com");
        member.setNickname("이세형");
        post.setMember(member);

        return postRepository.save(post);
    }

    // 게시글 수정 메서드
    public Post updatePost(Long postId, Post post) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));

        if (post.getTitle() != null) { // 제목에 대한 예외처리
            findPost.setTitle(post.getTitle());
        }

        if (post.getContent() != null) { // 내용에 대한 예외처리
            findPost.setContent(post.getContent());
        }
        return postRepository.save(findPost);
    }

    // 게시글 삭제 메서드
    public void deletePost(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));

        postRepository.delete(findPost);
    }
}
