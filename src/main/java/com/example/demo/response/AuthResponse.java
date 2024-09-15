package com.example.demo.response;

import com.example.demo.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Long id;
    private String username;
    private String email;

    public AuthResponse(String accessToken, Account user) {
        this.accessToken = accessToken;
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
