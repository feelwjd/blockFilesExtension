package com.example.blockfilesextension.controller;

import com.example.blockfilesextension.model.Extension;
import com.example.blockfilesextension.model.ExtensionHistory;
import com.example.blockfilesextension.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/extension/add")
    public ResponseEntity<String> addExtension(HttpSession session, @RequestBody Extension extension) {
        taskService.addExtension(session, extension);
        return ResponseEntity.ok("Extension added successfully!");
    }

    @PostMapping("/extension/addHistory")
    public ResponseEntity<String> addExtensionHistory(HttpSession session, @RequestBody ExtensionHistory extensionHistory) {
        taskService.addExtensionHistory(session, extensionHistory);
        return ResponseEntity.ok("ExtensionHistory added successfully!");
    }

    @GetMapping("/extension/history")
    public List<ExtensionHistory> getHistoryBySessionId(HttpSession session) {
        return taskService.getHistoryBySession(session);
    }

    @GetMapping("/extension/all")
    public List<Extension> getAllExtensions() {
        return taskService.getAllExtensions();
    }

    @PostMapping("/extension/check")
    public ResponseEntity<String> checkExtension(@RequestBody ExtensionHistory extensionHistory) {
        taskService.checkExtensionHistory(extensionHistory);
        return ResponseEntity.ok("Fixed extension status updated!");
    }

    @PostMapping("/extension/deleteHistory")
    public ResponseEntity<String> deleteExtensionHistory(@RequestBody ExtensionHistory extensionHistory) {
        taskService.deleteExtensionHistory(extensionHistory);
        return ResponseEntity.ok("ExtensionHistory deleted successfully!");
    }

    @GetMapping("/extension/top")
    public List<Extension> getTopExtensions() {
        return taskService.getTopExtensions();
    }
}
