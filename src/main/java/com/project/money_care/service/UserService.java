package com.project.money_care.service;

import com.project.money_care.enums.TokenValidation;
import com.project.money_care.model.Users;
import com.project.money_care.model.VerificationToken;

import java.util.Optional;

public interface UserService {
    Users findByEmail(String email);

    VerificationToken updateTokenExpiration(String token);

    Users newUserAccount(Users account);

    void createVerificationToken(Users user);

    TokenValidation validateVerificationToken(String token);
}
