package com.example.blockfilesextension.controller;

import com.example.blockfilesextension.util.CodeEx;
import com.example.blockfilesextension.util.GenerateResponse;
import com.example.blockfilesextension.model.ResponseBody;
import com.example.blockfilesextension.service.SessionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/create")
    public JsonNode createSession(HttpSession session) {

        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper mapper         = new ObjectMapper();;
        ResponseBody<String> body   = new ResponseBody<>();

        try{
            sessionService.createSession(session);
            body.setData(sessionId);

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while adding extension: {}", e.getMessage());
            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while adding session manage: :{" + CodeEx.DB_INSERT_ERROR + "}", body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }
}