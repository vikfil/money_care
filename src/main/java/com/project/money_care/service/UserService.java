package com.project.money_care.service;

import com.project.money_care.enums.TokenValidation;
import com.project.money_care.model.User;
import com.project.money_care.model.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    VerificationToken updateTokenExpiration(String token);
    User newUserAccount(User account);
    void createVerificationToken(User user);
    TokenValidation validateVerificationToken(String token);
}
