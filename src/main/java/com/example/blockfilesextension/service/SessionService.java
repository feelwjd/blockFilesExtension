package com.example.blockfilesextension.service;

import jakarta.websocket.Session;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    Session createSession();
    boolean isValidSession(String sessionId);
}
