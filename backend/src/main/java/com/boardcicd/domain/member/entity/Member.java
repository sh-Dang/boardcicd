package com.boardcicd.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 100, unique = true)
    private String email; // email

    @Column(nullable = false, length = 20)
    private String nickname; // 닉네임

    @Column(nullable = false, length = 255)
    private String password; // 비밀번호 암호화 저장

    @Column(nullable = false, length = 50, unique = true)
    private String username; // 유저이름

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}