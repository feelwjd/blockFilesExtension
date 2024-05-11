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
            ", select_count " +
            ") VALUES (" +
            "#{extensionName}" +
            ", SYSDATE()" +
            ", SYSDATE()" +
            ", 1" +
            ") ON DUPLICATE KEY UPDATE " +
            "update_date = SYSDATE() " +
            ", select_count = select_count + 1")
    @Options(useGeneratedKeys = true, keyProperty = "extensionIndex")
    int insertExtension(Extension extension);

    //확장자 히스토리 생성
    @Update("INSERT INTO BLOCK_FILE_EXTENSION_HISTORY (" +
            "extension_index" +
            ", session_id" +
            ", create_date" +
            ", update_date" +
            ") VALUES (" +
            "#{extensionIndex}" +
            ", #{sessionId}" +
            ", SYSDATE()" +
            ", SYSDATE()" +
            ") ON DUPLICATE KEY UPDATE " +
            "del_yn = 'N'" +
            ", update_date = SYSDATE()")
    void insertExtensionHistory(ExtensionHistory extensionHistory);

    //세션 별 사용자 히스토리 조회
    @Select("SELECT " +
            "HST.history_index      AS historyIndex" +
            ", HST.session_id       AS sessionId" +
            ", HST.extension_index  AS extensionIndex" +
            ", MST.extension_name   AS extensionName" +
            ", HST.check_yn         AS checked" +
            ", HST.create_date      AS createDate" +
            ", HST.update_date      AS updateDate " +
            "FROM BLOCK_EXTENSION.BLOCK_FILE_EXTENSION_HISTORY      HST " +
            "INNER JOIN BLOCK_EXTENSION.BLOCK_FILE_EXTENSION_MASTER MST ON MST.extension_index = HST.extension_index " +
            "WHERE session_id = #{sessionId} " +
            "AND del_yn = 'N' ")
    List<ExtensionHistory> selectHistoryBySessionId(String sessionId);

    //세션 별 사용자 확장자 히스토리 업데이트
    @Update("UPDATE BLOCK_FILE_EXTENSION_HISTORY " +
            "SET " +
            "check_yn = #{checked} " +
            "WHERE session_id = #{sessionId} " +
            "AND extension_index = #{extensionIndex}")
    void updateExtension(ExtensionHistory extension);

    @Update("UPDATE BLOCK_FILE_EXTENSION_MASTER " +
            "SET " +
            "select_count = CASE WHEN #{checked} = 'Y' THEN select_count + 1 ELSE select_count END " +
            "WHERE extension_index = #{extensionIndex} ")
    void updateSelectedExtension(ExtensionHistory extensionHistory);

    //세션 별 사용자 확장자 히스토리 삭제
    @Update("UPDATE BLOCK_FILE_EXTENSION_HISTORY " +
            "SET " +
            "del_yn = 'Y' " +
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

    //고정 확장자 조회
    @Select("SELECT DISTINCT " +
            "MST.extension_index    AS extensionIndex " +
            ", MST.extension_name   AS extensionName " +
            ", MST.select_count     AS selectCount " +
            ", IF(HST.session_id = #{sessionId}, HST.check_yn, NULL) AS checked " +
            "FROM BLOCK_FILE_EXTENSION_MASTER MST " +
            "LEFT JOIN BLOCK_FILE_EXTENSION_HISTORY HST ON HST.extension_index = MST.extension_index " +
            "ORDER BY MST.select_count desc " +
            "LIMIT 5 ")
    List<ExtensionHistory> selectTopExtensions(String sessionId);
}
