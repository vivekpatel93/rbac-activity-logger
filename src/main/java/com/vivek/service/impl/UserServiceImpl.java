package com.vivek.service.impl;

import com.vivek.dto.UserRegisterDTO;
import com.vivek.dto.UserResponseDTO;
import com.vivek.entity.User;
import com.vivek.repository.UserRepository;
import com.vivek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    private User dtoToEntity(UserRegisterDTO dto){
        User user=new User();
        user.setUsername(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        return user;
    }

    private UserResponseDTO entityToDto(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getUserId());
        dto.setUserName(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }

    @Override
    public UserResponseDTO createUser(UserRegisterDTO dto){
        return entityToDto(userRepository.save(dtoToEntity(dto)));
    }

}
