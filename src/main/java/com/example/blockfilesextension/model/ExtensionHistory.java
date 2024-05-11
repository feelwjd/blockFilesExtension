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
    private String delYn;
    private int select_count;
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
                ", select_count=" + select_count +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", delYn='" + delYn + '\'' +
                '}';
    }
}
