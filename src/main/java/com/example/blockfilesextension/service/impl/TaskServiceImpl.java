package com.example.blockfilesextension.service.impl;

import com.example.blockfilesextension.mapper.ExtensionMapper;
import com.example.blockfilesextension.model.Extension;
import com.example.blockfilesextension.service.TaskService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final SqlSession sqlSession;

    @Autowired
    public TaskServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Extension> getAllExtensions(String sessionId) {
        ExtensionMapper extensionMapper = sqlSession.getMapper(ExtensionMapper.class);
        return extensionMapper.getAllExtensions(sessionId);
    }
}
