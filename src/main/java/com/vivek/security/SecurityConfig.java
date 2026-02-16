package com.vivek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    // Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                // Enable CORS properly
                .cors(cors -> cors.disable()) // We use CorsConfig class

                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // Stateless session
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Authorization
                .authorizeHttpRequests(auth -> auth

                        // Public
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // Admin only
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers("/user/create").hasRole("ADMIN")
                        // Admin + Manager
                        .requestMatchers("/logs/**")
                        .hasAnyRole("ADMIN", "MANAGER")

                        // User only
                        .requestMatchers("/user/**").hasRole("USER")

                        // Others
                        .anyRequest().authenticated()
                )

                // JWT Filter
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class)

                // H2 Console
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
