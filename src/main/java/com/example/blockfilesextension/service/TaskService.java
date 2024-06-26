package com.example.blockfilesextension.service;

import com.example.blockfilesextension.model.Extension;
import com.example.blockfilesextension.model.ExtensionHistory;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    int addExtension(HttpSession session, Extension extension);
    void addExtensionHistory(HttpSession session, ExtensionHistory extensionHistory);
    void checkExtensionHistory(HttpSession session, ExtensionHistory extensionHistory);
    void deleteExtensionHistory(ExtensionHistory extensionHistory);
    List<ExtensionHistory> getHistoryBySession(HttpSession session);
    List<Extension> getAllExtensions();
    List<ExtensionHistory> getTopExtensions(HttpSession session);
}
