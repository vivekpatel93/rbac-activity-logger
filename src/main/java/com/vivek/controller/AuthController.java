package com.vivek.controller;


import com.vivek.dto.LoginRequestDTO;
import com.vivek.dto.LoginResponse;
import com.vivek.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequestDTO dto) {

        return service.login(dto);
    }
}
