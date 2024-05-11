package com.example.blockfilesextension.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class Extension {
    private int extensionIndex;
    private String extensionName;
    private int selectCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Override
    public String toString() {
        return "Extension{" +
                "extensionIndex=" + extensionIndex +
                ", extensionName='" + extensionName + '\'' +
                ", selectCount=" + selectCount +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
