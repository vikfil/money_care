package com.project.money_care.enums;

public enum TokenValidation {
    TOKEN_INVALID("invalidToken"),
    TOKEN_EXPIRED("expired"),
    TOKEN_VALID("valid");

    private String code;

    TokenValidation(String code) {
        this.code = code;
    }
}
