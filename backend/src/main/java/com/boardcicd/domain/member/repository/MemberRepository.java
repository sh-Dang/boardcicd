package com.boardcicd.domain.member.repository;

import com.boardcicd.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    Member findMemberById(Long id);
}
