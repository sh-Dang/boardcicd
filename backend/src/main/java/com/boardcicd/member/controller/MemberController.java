package com.boardcicd.member.controller;

import com.boardcicd.member.dto.LoginRequest;
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
public class MemberController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        System.out.println("가져온 유저네임: " + request.getUsername());
        System.out.println("가져온 비밀번호: " + request.getPassword());

        // TODO: 로그인 검증 로직 추가 후 양식에 맞는 reponse 객체 반환

        return "ok";
    }
}
