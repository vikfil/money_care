package com.project.money_care.controller;

import com.project.money_care.dto.UserDto;
import com.project.money_care.enums.TokenValidation;
import com.project.money_care.mapper.UserMapper;
import com.project.money_care.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("security")
public class RegistrationController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(value = "/registration")
    public ResponseEntity registerUserAccount(@RequestBody UserDto accountDto) {
        UserDto registered = userMapper.userToDto(userService.newUserAccount(userMapper.toUser(accountDto)));
        userService.createVerificationToken(userMapper.toUser(registered));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/confirm")
    public ResponseEntity confirmRegistration(@RequestParam("token") String token) {
        TokenValidation result = userService.validateVerificationToken(token);
        if (TokenValidation.TOKEN_VALID.equals(result)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/resendRegistrationToken")
    public ResponseEntity resendRegistrationToken(@RequestParam("token") String existingToken) {
        userService.updateTokenExpiration(existingToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public Principal getLogin(Principal user) {
        return user;
    }
}
