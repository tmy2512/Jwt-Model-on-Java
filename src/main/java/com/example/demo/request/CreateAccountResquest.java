package com.example.demo.request;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class CreateAccountResquest {

    private String fullname;
    private String username;
    private String email;
    private String password;

    public Account asAccount() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Account account = new Account();
        account.setRole(Role.USER);
        account.setEmail(this.email);
        account.setFullname(this.fullname);
        account.setUsername(this.username);
        account.setPassword(encoder.encode(this.password));
        return account;
    }
}
