package com.example.blockfilesextension.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class UserSession {
    private String sessionId;
    private LocalDateTime createDate;
    private LocalDateTime expiredDate;
}