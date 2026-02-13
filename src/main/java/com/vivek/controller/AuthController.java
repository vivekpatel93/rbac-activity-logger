package com.vivek.controller;

import com.vivek.dto.AuthResponseDTO;
import com.vivek.dto.LoginRequestDTO;
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
    public AuthResponseDTO login(
            @RequestBody LoginRequestDTO dto) {

        String token = service.login(dto);

        return new AuthResponseDTO(token);
    }
}
