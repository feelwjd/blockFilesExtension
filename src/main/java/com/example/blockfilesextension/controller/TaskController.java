package com.example.blockfilesextension.controller;

import com.example.blockfilesextension.model.*;
import com.example.blockfilesextension.model.ResponseBody;
import com.example.blockfilesextension.service.TaskService;
import com.example.blockfilesextension.util.CodeEx;
import com.example.blockfilesextension.util.GenerateResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 확장자 추가
     *
     * @param session 현재 세션의 HttpSession object.
     * @param object 추가하는 확장자 Object
     * @return JsonNode
     */
    @PostMapping("/extension/add")
    public JsonNode addExtension(HttpSession session, @RequestBody Object object) {
        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper            mapper      = new ObjectMapper();;
        ResponseBody<Integer>   body        = new ResponseBody<>();
        try{
            Extension extension = mapper.readValue(mapper.writeValueAsString(object), Extension.class);
            taskService.addExtension(session, extension);
            body.setData(extension.getExtensionIndex());

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while adding extension: {}", e.getMessage());
            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while adding extension: " + e.getMessage(), body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }

    /**
     * 해당 세션의 확장자 히스토리 추가
     *
     * @param session 현재 세션의 HttpSession object.
     * @param object 추가하는 확장자 히스토리 Object.
     * @return JsonNode
     */
    @PostMapping("/extension/addHistory")
    public JsonNode addExtensionHistory(HttpSession session, @RequestBody Object object) {

        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper            mapper      = new ObjectMapper();;
        ResponseBody<String>    body        = new ResponseBody<>();
        try{
            ExtensionHistory extensionHistory = mapper.readValue(mapper.writeValueAsString(object), ExtensionHistory.class);
            taskService.addExtensionHistory(session, extensionHistory);

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while adding extension history: {}", e.getMessage());

            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while adding extension history: :{" + CodeEx.DB_INSERT_ERROR + "}", body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }

    /**
     * 지정된 세션에 대한 확장자 히스토리 검색.
     *
     * @param session 현재 세션의 HttpSession object.
     * @return JsonNode
     */
    @GetMapping("/extension/history")
    public JsonNode getHistoryBySessionId(HttpSession session) {
        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper                            mapper      = new ObjectMapper();;
        ResponseBody<List<ExtensionHistory>>    body        = new ResponseBody<>();

        try{
            body.setData(taskService.getHistoryBySession(session));

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while getting extension history: {}", e.getMessage());

            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while getting extension history: :{" + CodeEx.DB_UNKNOWN_ERROR + "}", body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }

    /**
     * 모든 확장자 검색.
     *
     * @param session 현재 세션의 HttpSession object.
     * @return JsonNode
     */
    @GetMapping("/extension/all")
    public JsonNode getAllExtensions(HttpSession session) {
        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper                     mapper      = new ObjectMapper();;
        ResponseBody<List<Extension>>    body        = new ResponseBody<>();

        try{
            body.setData(taskService.getAllExtensions());

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while select all extension: {}", e.getMessage());

            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while select all extension: :{" + CodeEx.DB_UNKNOWN_ERROR + "}", body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }

    /**
     * 해당 세션의 확장자 체크 상태 응답.
     *
     * @param session           현재 세션의 HttpSession object.
     * @param object  체크 되어있는 확장자 기록 Object
     * @return JsonNode
     */
    @PostMapping("/extension/check")
    public JsonNode checkExtension(HttpSession session, @RequestBody Object object) {
        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper                     mapper      = new ObjectMapper();;
        ResponseBody<List<Extension>>    body        = new ResponseBody<>();

        try{
            ExtensionHistory extensionHistory = mapper.readValue(mapper.writeValueAsString(object), ExtensionHistory.class);
            taskService.checkExtensionHistory(extensionHistory);

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while updating extension: {}", e.getMessage());

            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while updating extension: :{" + CodeEx.DB_UPDATE_ERROR + "}", body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }

    /**
     * 해당 세션에서 확장자 삭제 시 확장자 히스토리 삭제
     *
     * @param session 현재 세션의 HttpSession object.
     * @param object 삭제하려는 확장자 히스토리 Object.
     * @return JsonNode
     */
    @PostMapping("/extension/deleteHistory")
    public JsonNode deleteExtensionHistory(HttpSession session, @RequestBody Object object) {
        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper            mapper      = new ObjectMapper();;
        ResponseBody<String>    body        = new ResponseBody<>();

        try{
            ExtensionHistory extensionHistory = mapper.readValue(mapper.writeValueAsString(object), ExtensionHistory.class);
            extensionHistory.setSessionId(sessionId);
            taskService.deleteExtensionHistory(extensionHistory);

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while delete extension: {}", e.getMessage());

            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while delete extension: :{" + CodeEx.DB_DELETE_ERROR + "}", body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }

    /**
     * 세션 기준의 상위 선택 확장자 검색.
     *
     * @param session 현재 세션의 HttpSession object.
     * @return JsonNode
     */
    @GetMapping("/extension/top")
    public JsonNode getTopExtensions(HttpSession session) {
        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper                     mapper             = new ObjectMapper();;
        ResponseBody<List<ExtensionHistory>>    body        = new ResponseBody<>();

        try{
            body.setData(taskService.getTopExtensions());

            return new GenerateResponse<>(CodeEx.OK, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            logger.error("Error occurred while select top extension: {}", e.getMessage());

            return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while select top extension: :{" + CodeEx.DB_UNKNOWN_ERROR + "}", body)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }
}
