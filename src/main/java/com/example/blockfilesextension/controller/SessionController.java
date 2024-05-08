package com.example.blockfilesextension.controller;

import com.example.blockfilesextension.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/session/create")
    public String createSession(HttpSession session) {
        // 세션 생성 및 세션 아이디 반환
        return sessionService.createSession(session);
    }
}