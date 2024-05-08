package com.example.blockfilesextension.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExtensionHistory {
    private String historyIndex;
    private String sessionId;
    private String extensionIndex;
    private String extensionName;
    private String checked;
    private Integer selectCount;
    private String createDate;
    private String updateDate;

    @Override
    public String toString() {
        return "ExtensionHistory{" +
                "historyIndex='" + historyIndex + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", extensionIndex='" + extensionIndex + '\'' +
                ", extensionName='" + extensionName + '\'' +
                ", checked='" + checked + '\'' +
                ", checked='" + checked + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
