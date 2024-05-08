package com.example.blockfilesextension.controller;

import com.example.blockfilesextension.model.ExtensionHistory;
import com.example.blockfilesextension.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    private static final String UPLOADED_FOLDER = "C://WorkSpace/Uploads/";
    private final TaskService taskService;

    public FileController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(HttpSession session, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.OK);
        }

        // Check if extension is not allowed for upload
        String fileExtension = getFileExtension(file);
        List<ExtensionHistory> userExtensions = taskService.getHistoryBySession(session);

        for (ExtensionHistory ext : userExtensions) {
            // If user has Extension on list, it means it is not allowed to be uploaded
            if (ext.getExtensionName().equals(fileExtension)) {
                return new ResponseEntity<>("File extension not allowed!", HttpStatus.FORBIDDEN);
            }
        }

        try {
            // Store the file
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
    }

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
}
