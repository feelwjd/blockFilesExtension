package com.example.blockfilesextension.service.impl;

import com.example.blockfilesextension.mapper.SessionMapper;
import com.example.blockfilesextension.model.UserSession;
import com.example.blockfilesextension.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;

    public SessionServiceImpl(SessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    @Override
    public HttpSession createSession(HttpSession session) {
        UserSession userSession = new UserSession();

        userSession.setSessionId(session.getId());
        userSession.setCreateDate(LocalDateTime.now());
        userSession.setExpiredDate(LocalDateTime.now().plusHours(1));

        sessionMapper.insertSession(userSession);

        return session;
    }
}
