package com.boardcicd.member.controller;

import com.boardcicd.member.dto.LoginRequest;
import com.boardcicd.member.dto.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인, 회원가입등 유저에 관련된 요청을 처리하기 위한 컨트롤러
 *
 * @author 이세형
 * @since 2026/03/24
 * */
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class MemberController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        log.debug("가져온 유저네임: {}", request.getUsername());
        log.debug("가져온 비밀번호: {}", request.getPassword());

        // TODO: 로그인 검증 로직 추가 후 양식에 맞는 response 객체 반환

        return "ok";
    }

    /**
     * 유저의 회원가입시도를 받아들이는 메서드
     *
     * @param request 프론트엔드에서 들어온 요청객체
     * @return 응답객체 생성 후 return하기
     */
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequest request) {
        log.debug("회원가입 시도한 user == {}", request.getUsername());
        log.debug("회원가입 시도한 password == {}", request.getPassword());

        // TODO: 회원가입 검증 로직 추가 후 양식에 맞는 response 객체 반환
        // DB에 저장 및 중복되는 ID가 있는지 검증하는 로직 추가 등
        return "ok";
    }
}
