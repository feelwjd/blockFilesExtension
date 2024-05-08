package com.example.blockfilesextension.service;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    String createSession(HttpSession session);
}
