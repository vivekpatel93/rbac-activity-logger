package com.vivek.service.impl;

import com.vivek.dto.LoginRequestDTO;
import com.vivek.dto.LoginResponse;
import com.vivek.entity.User;
import com.vivek.repository.UserRepository;
import com.vivek.security.JwtUtil;
import com.vivek.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtil;
    private final LoginActivityService loginActivityService;
    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder encoder,
                           JwtUtil jwtUtil,
                           LoginActivityService loginActivityService){
            this.userRepository=userRepository;
            this.encoder=encoder;
            this.jwtUtil=jwtUtil;
            this.loginActivityService=loginActivityService;
    }

    @Override
    public LoginResponse login(LoginRequestDTO dto){
        User user = userRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid username or password"));

        //  Check encrypted password
        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        //  Generate Token
        String token = jwtUtil.generateToken(user.getUsername(),
                user.getRole());
        //  LOG MANAGER LOGIN
        if ("MANAGER".equals(user.getRole())) {
            loginActivityService.save(user.getUsername(), "LOGIN");
        }
        return new LoginResponse(token, user.getRole());
    }

}
