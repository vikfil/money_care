package com.project.money_care.service.impl;

import com.project.money_care.enums.TokenValidation;
import com.project.money_care.exception.EmailExistException;
import com.project.money_care.exception.UserNotFound;
import com.project.money_care.model.User;
import com.project.money_care.model.VerificationToken;
import com.project.money_care.repository.UserRepository;
import com.project.money_care.repository.VerificationTokenRepository;
import com.project.money_care.service.EmailService;
import com.project.money_care.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, VerificationTokenRepository tokenRepository, @Lazy EmailService emailService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND, userEmail)));
    }

    @Override
    public VerificationToken updateTokenExpiration(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        verificationToken.updateToken(UUID.randomUUID().toString());
        emailConfirm(verificationToken.getUser().getEmail(), verificationToken.getToken());
        return tokenRepository.save(verificationToken);
    }

    @Override
    public User newUserAccount(User userAccount) {
        if (emailExists(userAccount.getEmail())) {
            throw new EmailExistException("There is an account with that email address: " + userAccount.getEmail());
        }
        User user = new User();
        user.setEmail(userAccount.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken vToken = new VerificationToken(token, user);
        emailConfirm(user.getEmail(), token);
        tokenRepository.save(vToken);
    }

    @Override
    public TokenValidation validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TokenValidation.TOKEN_INVALID;
        }

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return TokenValidation.TOKEN_EXPIRED;
        }
        final User user = verificationToken.getUser();
        user.setActive(true);
        userRepository.save(user);
        return TokenValidation.TOKEN_VALID;
    }

    private void emailConfirm(String email, String token) {
        String subject = "Registration Confirmation";
        String confirmationUrl ="http://localhost:8080/security/confirm?token=" + token;
        String message = "Thank you for registration. Please click on the below link to activate your account.";
        emailService.sendMail(email,subject,message + confirmationUrl);
    }
}
