package com.vivek.controller;

import com.vivek.dto.UserRegisterDTO;
import com.vivek.dto.UserResponseDTO;
import com.vivek.entity.LoginActivity;
import com.vivek.repository.LoginActivityRepository;
import com.vivek.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LoginActivityRepository repo;

    @PostMapping("/create")
    public UserResponseDTO create(@RequestBody UserRegisterDTO dto){

        return userService.createUser(dto);
    }

    @GetMapping("/manager-activity")
    public List<LoginActivity> getManagerActivity() {

        return repo.findAll();
    }
}
