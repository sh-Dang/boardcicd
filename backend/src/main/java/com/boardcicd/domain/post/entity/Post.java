package com.boardcicd.domain.post.entity;

import com.boardcicd.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="posts") // DB명 수동매핑
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 200)
    private String title; // 글 제목

    @Column(nullable = false)
    private String content; // 글 내용

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 작성일자

    @UpdateTimestamp
    @Column(name = "updated_at") // 마지막 수정일자
    private LocalDateTime updatedAt;

    /**
     * Member와의 연관관계 (N:1)
     * posts.members_id → members.id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id", nullable = false)
    private Member member;
}