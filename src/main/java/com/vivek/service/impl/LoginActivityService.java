package com.vivek.service.impl;

import com.vivek.entity.LoginActivity;
import com.vivek.entity.User;
import com.vivek.repository.LoginActivityRepository;
import com.vivek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginActivityService {
    @Autowired
    private LoginActivityRepository repo;

    @Autowired
    private UserRepository userRepo;

    public void save(String username, String action) {

        User user = userRepo.findByUsername(username)
                .orElseThrow();

        // Only track MANAGER
        if (!user.getRole().equals("MANAGER")) return;

        LoginActivity log = new LoginActivity();

        log.setUserId(user.getUserId());
        log.setUsername(user.getUsername());
        log.setRole(user.getRole());
        log.setAction(action);

        repo.save(log);
    }
}
