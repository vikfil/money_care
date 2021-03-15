package com.project.money_care.model;

public enum Roles {
    USER("USER"),
    MANAGER("MANAGER");

    private String name;

    Roles(String name) {
        this.name = name;
    }
}
