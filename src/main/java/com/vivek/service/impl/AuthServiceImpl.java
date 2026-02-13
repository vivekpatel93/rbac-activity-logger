package com.vivek.service.impl;

import com.vivek.dto.LoginRequestDTO;
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

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,PasswordEncoder encoder,JwtUtil jwtUtil){
            this.userRepository=userRepository;
            this.encoder=encoder;
            this.jwtUtil=jwtUtil;

    }

    @Override
    public String login(LoginRequestDTO dto){
        User user=userRepository.findByUserName(dto.getUserName()).orElseThrow(()-> new RuntimeException("Invalid"));

        if(!encoder.matches(dto.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid");
        }

        return jwtUtil.generateToken(
                user.getUserName(),
                user.getRole()
        );
    }

}
