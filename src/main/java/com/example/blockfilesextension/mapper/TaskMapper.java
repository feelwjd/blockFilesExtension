package com.example.blockfilesextension.mapper;

import com.example.blockfilesextension.model.Extension;
import com.example.blockfilesextension.model.ExtensionHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    //확장자 마스터 생성
    @Insert("INSERT INTO BLOCK_FILE_EXTENSION_MASTER (" +
            "extension_name" +
            ", create_date" +
            ", update_date " +
            ") VALUES (" +
            "#{extensionName}" +
            ", SYSDATE()" +
            ", SYSDATE()" +
            ") ON DUPLICATE KEY UPDATE " +
            "update_date = SYSDATE()")
    @Options(useGeneratedKeys = true, keyProperty = "extensionIndex")
    int insertExtension(Extension extension);

    //확장자 히스토리 생성
    @Insert("INSERT INTO BLOCK_FILE_EXTENSION_HISTORY (" +
            "extension_index" +
            ", session_id" +
            ", select_count" +
            ", create_date" +
            ", update_date" +
            ") VALUES (" +
            "#{extensionIndex}" +
            ", #{sessionId}" +
            ", 0" +
            ", SYSDATE()" +
            ", SYSDATE()" +
            ")")
    void insertExtensionHistory(ExtensionHistory extensionHistory);

    //세션 별 사용자 히스토리 조회
    @Select("SELECT " +
            "HST.session_id         AS sessionId" +
            ", HST.extension_index  AS extensionIndex" +
            ", MST.extension_name   AS extensionName" +
            ", HST.check_yn         AS checked" +
            ", HST.select_count     AS selectCount" +
            ", HST.create_date      AS createDate" +
            ", HST.update_date      AS updateDate " +
            "FROM BLOCK_EXTENSION.BLOCK_FILE_EXTENSION_HISTORY      HST " +
            "INNER JOIN BLOCK_EXTENSION.BLOCK_FILE_EXTENSION_MASTER MST ON MST.extension_index = HST.extension_index " +
            "WHERE session_id = #{sessionId}")
    List<ExtensionHistory> selectHistoryBySessionId(String sessionId);

    //세션 별 사용자 확장자 히스토리 업데이트
    @Update("UPDATE BLOCK_FILE_EXTENSION_HISTORY " +
            "SET " +
            "check_yn = #{checked} " +
            "WHERE session_id = #{sessionId}")
    void updateExtension(ExtensionHistory extension);

    //세션 별 사용자 확장자 히스토리 삭제
    @Update("DELETE FROM BLOCK_FILE_EXTENSION_HISTORY " +
            "WHERE session_id = #{sessionId} " +
            "AND extension_index = #{extensionIndex}")
    void deleteExtension(ExtensionHistory extension);

    //서버 전체 등록된 확장자 리스트 조회
    @Select("SELECT DISTINCT " +
            "HST.extension_index    AS extensionIndex" +
            ", MST.extension_name   AS extensionName " +
            "FROM BLOCK_FILE_EXTENSION_HISTORY      HST " +
            "INNER JOIN BLOCK_FILE_EXTENSION_MASTER MST on HST.extension_index = MST.extension_index")
    List<Extension> selectAllExtensions();

    //세션 별 사용자 고정 확장자 조회
    @Select("SELECT " +
            "HST.history_index      AS historyIndex " +
            ", HST.session_id       AS sessionId " +
            ", HST.extension_index  AS extensionIndex " +
            ", MST.extension_name   AS extensionName " +
            ", HST.select_count     AS selectCount " +
            ", HST.check_yn         AS checked " +
            ", HST.create_date      AS createDate " +
            ", HST.update_date      AS updateDate " +
            "FROM BLOCK_FILE_EXTENSION_HISTORY      HST " +
            "INNER JOIN BLOCK_FILE_EXTENSION_MASTER MST ON MST.extension_index = HST.extension_index " +
            "WHERE HST.extension_index = #{extensionId} " +
            "ORDER BY HST.select_count DESC " +
            "LIMIT 7")
    List<ExtensionHistory> selectTopExtensions();
}
