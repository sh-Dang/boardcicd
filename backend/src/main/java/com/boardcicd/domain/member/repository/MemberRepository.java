package com.boardcicd.domain.member.repository;

import com.boardcicd.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberById(Long id);
}
