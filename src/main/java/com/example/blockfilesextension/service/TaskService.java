package com.example.blockfilesextension.service;

import com.example.blockfilesextension.model.Extension;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Extension> getAllExtensions(String sessionId);
//    void addExtension(String sessionId, String extensionName);
//    void increaseSelectionCount(String sessionId, int extensionIndex);
//    void fixExtension(String sessionId, int extensionIndex);
}
