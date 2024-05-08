package com.example.blockfilesextension.mapper;

import com.example.blockfilesextension.model.UserSession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SessionMapper {
    //세션 사용자 등록
    @Insert("INSERT INTO BLOCK_EXTENSION.SESSION_MANAGE (" +
            "session_id" +
            ", create_date" +
            ", expired_date" +
            ") VALUES (" +
            "#{sessionId}" +
            ", #{createDate}" +
            ", #{expiredDate}" +
            ")")
    void insertSession(UserSession userSession);
}
