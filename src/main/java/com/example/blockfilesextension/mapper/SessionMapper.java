package com.example.blockfilesextension.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface SessionMapper {
    void saveSession(@Param("sessionId") String sessionId, @Param("createDate") LocalDateTime createDate, @Param("expiredDate") LocalDateTime expiredDate);
}
