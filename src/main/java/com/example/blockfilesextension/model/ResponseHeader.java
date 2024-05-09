package com.example.blockfilesextension.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ResponseHeader {
    private LocalDateTime timestamp;
    private int status;
    private boolean result;
    private String error;
    private String message;
}
