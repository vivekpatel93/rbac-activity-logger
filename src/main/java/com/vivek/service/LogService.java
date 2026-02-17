package com.vivek.service;

import com.vivek.dto.LogDTO;

import java.util.List;

public interface LogService {

    List<LogDTO> getAllLogs();

    public void saveLogByUsername(String username, String button);
}
