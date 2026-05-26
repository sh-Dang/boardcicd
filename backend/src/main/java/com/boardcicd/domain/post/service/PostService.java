package com.boardcicd.domain.post.service;

import com.boardcicd.domain.auth.exception.AuthErrorCode;
import com.boardcicd.domain.auth.exception.AuthException;
import com.boardcicd.domain.member.entity.Member;
import com.boardcicd.domain.member.exception.MemberErrorCode;
import com.boardcicd.domain.member.exception.MemberException;
import com.boardcicd.domain.member.repository.MemberRepository;
import com.boardcicd.domain.post.dto.PostRequestDto;
import com.boardcicd.domain.post.entity.Post;
import com.boardcicd.domain.post.exception.PostErrorCode;
import com.boardcicd.domain.post.exception.PostException;
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
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_FOUND));
    }

    // 게시글 등록 메서드
    public Post createPost(PostRequestDto.Create postRequestDto) {
        Long memberId = (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.WRONG_EMAIL_OR_PASSWORD));

        // RequestDto를 Post에 매핑
        Post post = Post.create(postRequestDto.getTitle(), postRequestDto.getContent(), member);
//        post.setTitle(postRequestDto.getTitle());
//        post.setContent();
//        post.setMember(member);

        return postRepository.save(post);
    }

    // 게시글 수정 메서드
    public Post updatePost(Long postId, PostRequestDto.Update postRequestDto) throws PostException {
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        Long memberId = (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        // 작성자 본인 확인
        if (!foundPost.getMember().getId().equals(memberId)) {
            throw new PostException(PostErrorCode.NO_PERMISSION); // 권한없음 예외코드
        }

//        if (postRequestDto.getTitle() != null) { // 제목에 대한 예외처리
//            foundPost.setTitle(postRequestDto.getTitle());
//        }
//
//        if (postRequestDto.getContent() != null) { // 내용에 대한 예외처리
//            foundPost.setContent(postRequestDto.getContent());
//        }
        // post Entity 내부의 메서드를 사용하도록 변경
        if (postRequestDto.getTitle() != null && postRequestDto.getContent() != null){
            foundPost.update(postRequestDto.getTitle(), postRequestDto.getContent());
        }

        return postRepository.save(foundPost);
    }

    // 게시글 삭제 메서드
    public void deletePost(Long postId) throws PostException {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(PostErrorCode.POST_NOT_FOUND)); // 게시글없음 예외코드
        // 작성자 본인 확인
        Long memberId = (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (!findPost.getMember().getId().equals(memberId)) {
            throw new PostException(PostErrorCode.NO_PERMISSION); // 권한없음 예외
        }
        postRepository.delete(findPost);
    }
}
