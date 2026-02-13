package com.vivek.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthResponseDTO {

    private String token;

    public AuthResponseDTO(String token){
        this.token=token;
    }
}
