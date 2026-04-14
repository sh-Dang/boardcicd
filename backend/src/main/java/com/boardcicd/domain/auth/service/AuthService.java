package com.boardcicd.domain.auth.setvice;

import com.boardcicd.domain.member.dto.SignupResponseDto;
import com.boardcicd.domain.member.repository.MemberRepository;
import com.boardcicd.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
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
        if (memberRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 username입니다.");
        }

        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 email입니다.");
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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        String accessToken = jwtTokenProvider.createToken(authentication.getName());

        return new LoginResponseDto(accessToken, "Bearer");
    }
}