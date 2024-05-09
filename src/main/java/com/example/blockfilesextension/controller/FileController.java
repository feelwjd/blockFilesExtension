package com.example.blockfilesextension.controller;

import com.example.blockfilesextension.model.*;
import com.example.blockfilesextension.service.TaskService;
import com.example.blockfilesextension.util.CodeEx;
import com.example.blockfilesextension.util.GenerateResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${server.upload.nas.address}")
    private String UPLOADED_FOLDER;
    private final TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    public FileController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 제한하는 파일 확장자 제외 파일 업로드
     *
     * @param session 현재 세션의 HttpSession object.
     * @param file 업로드 하려는 파일.
     * @return JsonNode
     */
    @PostMapping("/uploadFile")
    public JsonNode uploadFile(HttpSession session, @RequestParam("file") MultipartFile file) {
        String sessionId = session.getId();
        logger.debug("Start::onProcess()");
        logger.debug("Session ID: {}", sessionId);

        ObjectMapper mapper         = new ObjectMapper();;
        ResponseBody<String> body   = new ResponseBody<>();

        try{
            if (file.isEmpty()) {
                logger.error("Please select a file!");
                return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Please select a file! :{" + CodeEx.FILE_NOT_FOUND + "}", null)
                        .generateResponse();
            }

            if (!validateFileType(file)) {
                logger.error("Invalid file type!");
                return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Invalid file type! :{" + CodeEx.FILE_UPLOAD_FORMAT_NOT_SUPPORTED + "}", null)
                        .generateResponse();
            }

            String fileExtension = getFileExtension(file);
            List<ExtensionHistory> userExtensions = taskService.getHistoryBySession(session);

            for (ExtensionHistory ext : userExtensions) {
                if (ext.getExtensionName().equals(fileExtension)) {
                    logger.error("File extension not allowed!");
                    return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "File extension not allowed! :{" + CodeEx.FILE_UPLOAD_FORMAT_NOT_SUPPORTED + "}", null)
                            .generateResponse();
                }
            }

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);

                if(!Files.exists(path)) {
                    throw new FileNotFoundException(UPLOADED_FOLDER + file.getOriginalFilename());
                }

            } catch (FileNotFoundException e) {
                logger.error("Error occurred while upload file: {}", e.getMessage());
                return new GenerateResponse<>(CodeEx.BAD_REQUEST, false, "Error occurred while upload file: :{" + CodeEx.FILE_UPLOAD_PARTIAL_ERROR + "}", null)
                        .generateResponse();
            }
            body.setData("File uploaded successfully!");
            return new GenerateResponse<>(200, true, "Success", body)
                    .generateResponse();
        } catch (Exception e) {
            return new GenerateResponse<>(500, false, "Error occurred while upload file: :{" + CodeEx.FILE_UPLOAD_FORMAT_NOT_SUPPORTED + "}", null)
                    .generateResponse();
        } finally {
            logger.debug("End::onProcess()");
        }
    }

    /**
     * MultipartFile 의 파일 확장자 가져오기.
     *
     * @param file 확장자를 얻으려는 file.
     * @return 확장자 return.
     */
    private String getFileExtension(MultipartFile file) {
        MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();

        String mimeType = file.getContentType();
        String extension = "";

        try {
            extension = mimeTypes.forName(mimeType).getExtension();
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }
        return extension;
    }

    /**
     * MultipartFile 파일 형식 위변조 검증
     *
     * @param file 위변조 검증하려는 파일
     * @return 검증이 된경우 true, 검증이 안된 경우 false
     */
    private boolean validateFileType(MultipartFile file) {
        try {
            String extension;

            Tika tika = new Tika();
            String detectedType = tika.detect(file.getBytes());

            // 파일 위변조 체크 Mapping
            extension = switch (detectedType) {
                case "application/pdf" -> ".pdf";
                case "image/jpeg" -> ".jpg";
                case "image/png" -> ".png";
                case "image/gif" -> ".gif";
                case "text/plain" -> ".txt";
                case "application/msword" -> ".doc";
                case "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> ".docx";
                case "application/vnd.ms-excel" -> ".xls";
                case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> ".xlsx";
                case "application/vnd.ms-powerpoint" -> ".ppt";
                case "application/vnd.openxmlformats-officedocument.presentationml.presentation" -> ".pptx";
                case "video/mpeg" -> ".mpeg";
                case "audio/mpeg" -> ".mp3";
                case "application/java-archive" -> ".jar";
                case "application/x-rar-compressed" ->  // .rar
                        ".rar";
                case "application/x-tar" ->  // .tar
                        ".tar";
                case "application/x-7z-compressed" ->  // .7z
                        ".7z";
                case "application/x-msdownload" -> ".exe";
                case "text/x-shellscript" -> ".sh";
                case "application/x-bat" ->  // .bat
                        ".bat";
                case "application/x-msdos-program" ->  // .com
                        ".com";
                case "text/x-java-source" -> ".java";
                case "text/javascript" -> ".js";
                case "text/x-python" -> ".py";
                case "application/x-ruby" -> ".rb";
                case "text/x-c++src" -> ".cpp";
                case "text/x-go" -> ".go";
                case "text/x-php" -> ".php";
                case "text/css" -> ".css";
                default -> "";
            };

            String originalFilename = file.getOriginalFilename();
            if(originalFilename == null || originalFilename.lastIndexOf('.') == -1) {
                return false;
            }
            if(!originalFilename.toLowerCase().endsWith(extension)) {
                return false;
            }
            return true;
        } catch(IOException e) {
            return false;
        }
    }
}
