package com.board.service;

import com.board.dto.request.PostRequest;
import com.board.dto.response.PostResponse;
import com.board.entity.Member;
import com.board.entity.Post;
import com.board.exception.BusinessException;
import com.board.repository.MemberRepository;
import com.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<PostResponse.Summary> getPosts(String keyword, Pageable pageable) {
        if (StringUtils.hasText(keyword)) {
            return postRepository
                    .findByTitleContainingOrContentContainingOrderByCreatedAtDesc(keyword, keyword, pageable)
                    .map(PostResponse.Summary::from);
        }
        return postRepository.findAllByOrderByCreatedAtDesc(pageable)
                .map(PostResponse.Summary::from);
    }

    @Transactional
    public PostResponse.Detail getPost(Long id) {
        Post post = findPostById(id);
        post.increaseViewCount();
        return PostResponse.Detail.from(post);
    }

    @Transactional
    public PostResponse.Detail createPost(PostRequest.Create request, String username) {
        Member member = findMemberByUsername(username);

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();

        return PostResponse.Detail.from(postRepository.save(post));
    }

    @Transactional
    public PostResponse.Detail updatePost(Long id, PostRequest.Update request, String username) {
        Post post = findPostById(id);
        validateAuthor(post, username);
        post.update(request.getTitle(), request.getContent());
        return PostResponse.Detail.from(post);
    }

    @Transactional
    public void deletePost(Long id, String username) {
        Post post = findPostById(id);
        validateAuthor(post, username);
        postRepository.delete(post);
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new BusinessException("게시글을 찾을 수 없습니다."));
    }

    private Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("회원을 찾을 수 없습니다."));
    }

    private void validateAuthor(Post post, String username) {
        if (!post.getMember().getUsername().equals(username)) {
            throw new BusinessException("수정/삭제 권한이 없습니다.");
        }
    }
}
