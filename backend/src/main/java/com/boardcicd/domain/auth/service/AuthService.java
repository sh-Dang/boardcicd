package com.boardcicd.domain.auth.service;

import com.boardcicd.domain.auth.exception.AuthErrorCode;
import com.boardcicd.domain.auth.exception.AuthException;
import com.boardcicd.domain.member.dto.LoginRequestDto;
import com.boardcicd.domain.member.dto.LoginResponseDto;
import com.boardcicd.domain.member.dto.SignupRequestDto;
import com.boardcicd.domain.member.dto.SignupResponseDto;
import com.boardcicd.domain.member.entity.Member;
import com.boardcicd.domain.member.exception.MemberErrorCode;
import com.boardcicd.domain.member.exception.MemberException;
import com.boardcicd.domain.member.repository.MemberRepository;
import com.boardcicd.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupResponseDto signup(SignupRequestDto dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }
        if (memberRepository.existsByNickname(dto.getNickname())) {
            throw new MemberException(MemberErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        Member member = new Member();
        member.setUsername(dto.getUsername());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setEmail(dto.getEmail());
        member.setNickname(dto.getNickname());

        Member saved = memberRepository.save(member);

        return new SignupResponseDto(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getNickname()
        );
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        // 들어온 로그인요청을 토대로 DB조회 → 올바른경우 SecretKey와 조합하여 JWT생성 후 반환
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        String email = authentication.getName();
        // 유저Id의 빠른 조회를 위해 Token 생성시 memberId도 담아서 제공
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)); // 존재하지 않은 경우

        String accessToken = jwtTokenProvider.createToken(email, member.getId());

        return new LoginResponseDto(accessToken);

    }
}