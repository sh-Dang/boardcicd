package com.boardcicd.domain.member.repository;

import com.boardcicd.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberById(Long id);
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
