package com.example.demo.service.impl;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.request.AuthRequest;
import com.example.demo.request.CreateAccountResquest;
import com.example.demo.response.AuthResponse;
import com.example.demo.service.AccountService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AccountServiceImpl(AccountRepository accountRepository, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.accountRepository = accountRepository;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = accountRepository.findByUsername(authRequest.getUsername());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse(token, account);
    }

    @Override
    public void createAccount(CreateAccountResquest resquest) {
        Account account = resquest.asAccount();
        accountRepository.save(account);
    }
}
