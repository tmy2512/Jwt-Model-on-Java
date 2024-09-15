package com.example.demo.service;

import com.example.demo.request.AuthRequest;
import com.example.demo.request.CreateAccountResquest;
import com.example.demo.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    AuthResponse login(AuthRequest authRequest);

    void createAccount(CreateAccountResquest resquest);
}
