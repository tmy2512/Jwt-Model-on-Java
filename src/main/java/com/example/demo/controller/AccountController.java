package com.example.demo.controller;

import com.example.demo.request.CreateAccountResquest;
import com.example.demo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("Hello ", HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody CreateAccountResquest resquest) {
        accountService.createAccount(resquest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
