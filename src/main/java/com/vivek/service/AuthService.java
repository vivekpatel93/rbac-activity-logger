package com.vivek.service;

import com.vivek.dto.LoginRequestDTO;
import com.vivek.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequestDTO dto);
}
