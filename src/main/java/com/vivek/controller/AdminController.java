package com.vivek.controller;

import com.vivek.dto.UserRegisterDTO;
import com.vivek.dto.UserResponseDTO;
import com.vivek.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/create")
    public UserResponseDTO create(@RequestBody UserRegisterDTO dto){

        return userService.createUser(dto);
    }
}
