package com.example.blockfilesextension.service.impl;

import com.example.blockfilesextension.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public String createSession(HttpSession session) {
        // 세션 아이디를 반환
        return session.getId();
    }
}
