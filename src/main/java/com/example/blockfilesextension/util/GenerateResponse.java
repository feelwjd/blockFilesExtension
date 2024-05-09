package com.example.blockfilesextension.util;

import com.example.blockfilesextension.model.Response;
import com.example.blockfilesextension.model.ResponseBody;
import com.example.blockfilesextension.model.ResponseHeader;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;

public class GenerateResponse<T> {

    private int status;
    private boolean result;
    private String message;
    private ResponseBody<T> body;

    public GenerateResponse(int status, boolean result, String message, ResponseBody<T> body) {
        this.status = status;
        this.result = result;
        this.body = body;
        this.message = message;
    }

    public JsonNode generateResponse() {
        Response<T> response = new Response<>();
        ResponseHeader header = new ResponseHeader();

        header.setTimestamp(LocalDateTime.now());
        header.setStatus(status);
        header.setResult(result);
        header.setMessage(message);

        response.setHeader(header);
        response.setBody(body);

        return response.toJsonNode();
    }
}
