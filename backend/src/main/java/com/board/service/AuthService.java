package com.board.service;

import com.board.dto.request.AuthRequest;
import com.board.dto.response.AuthResponse;
import com.board.entity.Member;
import com.board.exception.BusinessException;
import com.board.repository.MemberRepository;
import com.board.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signUp(AuthRequest.SignUp request) {
        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("이미 사용 중인 아이디입니다.");
        }
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("이미 사용 중인 이메일입니다.");
        }

        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .nickname(request.getNickname())
                .build();

        memberRepository.save(member);
    }

    public AuthResponse login(AuthRequest.Login request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(userDetails.getUsername());

        Member member = memberRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new BusinessException("회원을 찾을 수 없습니다."));

        return AuthResponse.builder()
                .token(token)
                .username(member.getUsername())
                .nickname(member.getNickname())
                .build();
    }
}
