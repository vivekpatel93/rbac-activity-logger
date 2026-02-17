package com.vivek.controller;

import com.vivek.dto.LogDTO;
import com.vivek.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class LogController {

    @Autowired
    private LogService service;

    @PostMapping("/user/log")
    public void log(
            @RequestParam String button,
            Principal principal) {
        String username= principal.getName();
        service.saveLogByUsername(username,button);
    }

    @GetMapping("/logs/all")
    public List<LogDTO> logs() {

        return service.getAllLogs();
    }
}

