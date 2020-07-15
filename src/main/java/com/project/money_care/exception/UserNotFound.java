package com.project.money_care.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("No such user");
    }
}
