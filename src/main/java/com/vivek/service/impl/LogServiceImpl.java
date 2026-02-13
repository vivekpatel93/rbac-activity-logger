package com.vivek.service.impl;

import com.vivek.dto.LogDTO;
import com.vivek.entity.ActivityLog;
import com.vivek.repository.ActivityLogRepository;
import com.vivek.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LogServiceImpl implements LogService {
    @Autowired
    private ActivityLogRepository repo;

    private LogDTO mapToDTO(ActivityLog log) {

        LogDTO dto = new LogDTO();

        dto.setId(log.getId());
        dto.setUserId(log.getUserId());
        dto.setRole(log.getRole());
        dto.setButton(log.getButton());
        dto.setTimestamp(log.getTimestamp());

        return dto;
    }
    @Override
    public void saveLog(Long userId,
                        String role,
                        String button) {

        ActivityLog log = new ActivityLog();

        log.setUserId(userId);
        log.setRole(role);
        log.setButton(button);
        log.setTimestamp(LocalDateTime.now());

        repo.save(log);
    }

    @Override
    public List<LogDTO> getAllLogs() {

        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


}
