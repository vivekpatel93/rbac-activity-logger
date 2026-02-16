package com.vivek.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        //  Skip preflight (CORS)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        //  Skip login API
        if (request.getServletPath().startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {

                if (jwtUtil.validate(token)) {

                    String username =
                            jwtUtil.extractUsername(token);

                    String role =
                            jwtUtil.extractRole(token);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    List.of(
                                            new SimpleGrantedAuthority("ROLE_" + role)
                                    )
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(auth);
                }

            } catch (Exception e) {

                // ❗ Invalid token
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }

}
