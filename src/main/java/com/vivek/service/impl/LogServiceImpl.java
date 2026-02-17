package com.vivek.service.impl;

import com.vivek.dto.LogDTO;
import com.vivek.entity.ActivityLog;
import com.vivek.entity.User;
import com.vivek.repository.ActivityLogRepository;
import com.vivek.repository.UserRepository;
import com.vivek.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private ActivityLogRepository repo;
    @Autowired
    private UserRepository userRepository;

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
    public void saveLogByUsername(String username, String button) {

        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        ActivityLog log = new ActivityLog();

        log.setUserId(user.getUserId());
        log.setRole(user.getRole());
        log.setButton(button);

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
