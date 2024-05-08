package com.example.blockfilesextension.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Extension {
    private int extensionIndex;
    private String sessionId;
    private String extensionName;
    private int selectCount;
    private boolean fixed;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Override
    public String toString() {
        return "Extention{" +
                "extensionIndex=" + extensionIndex +
                ", sessionId='" + sessionId + '\'' +
                ", extensionName='" + extensionName + '\'' +
                ", selectCount=" + selectCount +
                ", fixed=" + fixed +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
