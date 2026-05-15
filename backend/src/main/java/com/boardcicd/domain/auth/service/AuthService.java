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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    // 응답 객체의 실제 JSON 직렬화 형태를 확인하기 위한 ObjectMapper
    private final ObjectMapper objectMapper;

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

    public LoginResponseDto login(LoginRequestDto dto) throws JsonProcessingException {
        log.debug("요청들어온 객체: {}", dto);
        log.debug("요청들어온 객체 JSON: {}",objectMapper.writeValueAsString(dto));
        // 들어온 로그인요청을 토대로 DB조회 → 올바른경우 SecretKey와 조합하여 JWT생성 후 반환
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        String email = dto.getEmail();
        // 유저Id의 빠른 조회를 위해 Token 생성시 memberId도 담아서 제공
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.WRONG_EMAIL_OR_PASSWORD)); // 존재하지 않은 경우

        String accessToken = jwtTokenProvider.createToken(email, member.getId());

        return new LoginResponseDto(accessToken);

    }
}