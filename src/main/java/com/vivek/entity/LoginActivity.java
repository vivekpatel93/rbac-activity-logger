package com.vivek.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginActivity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String username;

    private String role;

    private String action;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
