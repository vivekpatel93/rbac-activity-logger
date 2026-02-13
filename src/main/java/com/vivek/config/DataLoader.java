package com.vivek.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vivek.entity.User;
import com.vivek.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(
            UserRepository userRepo,
            PasswordEncoder encoder) {

        return args -> {

            // Check if admin already exists
            if (userRepo.findByUsername("admin").isEmpty()) {

                User admin = new User();

                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");

                userRepo.save(admin);

                System.out.println("✅ Default ADMIN created");
            }
        };
    }
}
