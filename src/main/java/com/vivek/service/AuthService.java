package com.vivek.service;

import com.vivek.dto.LoginRequestDTO;

public interface AuthService {

    String login(LoginRequestDTO dto);
}
