package com.vivek.service;

import com.vivek.dto.UserRegisterDTO;
import com.vivek.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRegisterDTO dto);
}
