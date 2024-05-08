package com.example.blockfilesextension.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Session {
    private String sessionId;
    private LocalDateTime createDate;
    private LocalDateTime expiredDate;

    // 생성자
    public Session(String sessionId, LocalDateTime createDate, LocalDateTime expiredDate) {
        this.sessionId = sessionId;
        this.createDate = createDate;
        this.expiredDate = expiredDate;
    }

}
