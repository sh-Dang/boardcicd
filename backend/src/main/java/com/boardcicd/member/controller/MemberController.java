package com.boardcicd.member.controller;

import com.boardcicd.member.dto.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class MemberController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        System.out.println("가져온 유저네임: " + request.getUsername());
        System.out.println("가져온 비밀번호: " + request.getPassword());

        // TODO: 로그인 검증 로직

        return "ok";
    }
}
