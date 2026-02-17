package com.vivek.controller;


import com.vivek.dto.LoginRequestDTO;
import com.vivek.dto.LoginResponse;
import com.vivek.entity.LoginActivity;
import com.vivek.service.AuthService;
import com.vivek.service.impl.LoginActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;
    @Autowired
    private LoginActivityService loginActivityService;

    // Login
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequestDTO dto) {

        return service.login(dto);
    }

    // LOGOUT
    @PostMapping("/logout")
    public void logout() {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {

            String username = auth.getName(); //  from JWT

            loginActivityService.save(username, "LOGOUT");
        }
    }
}
