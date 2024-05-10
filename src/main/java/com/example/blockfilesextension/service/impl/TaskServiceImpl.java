package com.example.blockfilesextension.service.impl;

import com.example.blockfilesextension.mapper.TaskMapper;
import com.example.blockfilesextension.model.Extension;
import com.example.blockfilesextension.model.ExtensionHistory;
import com.example.blockfilesextension.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Extension> getAllExtensions() {
        return taskMapper.selectAllExtensions();
    }

    @Override
    public int addExtension(HttpSession session, Extension extension) {
        return taskMapper.insertExtension(extension);
    }

    @Override
    public void addExtensionHistory(HttpSession session, ExtensionHistory extensionHistory) {
        String sessionId = session.getId();
        extensionHistory.setSessionId(sessionId);
        extensionHistory.setSelectCount(0);
        taskMapper.insertExtensionHistory(extensionHistory);
    }

    @Override
    public List<ExtensionHistory> getHistoryBySession(HttpSession session) {
        return taskMapper.selectHistoryBySessionId(session.getId());
    }

    @Override
    public void checkExtensionHistory(HttpSession session, ExtensionHistory extensionHistory) {
        extensionHistory.setChecked(extensionHistory.getChecked());
        extensionHistory.setSessionId(session.getId());
        taskMapper.updateExtension(extensionHistory);
    }

    @Override
    public void deleteExtensionHistory(ExtensionHistory extensionHistory) {
        taskMapper.deleteExtension(extensionHistory);
    }

    @Override
    public List<ExtensionHistory> getTopExtensions(HttpSession session) {
        return taskMapper.selectTopExtensions(session.getId());
    }
}
