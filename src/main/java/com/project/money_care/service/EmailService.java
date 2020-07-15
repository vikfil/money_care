package com.project.money_care.service;

public interface EmailService {
    void sendMail(String recipientAddress, String subject, String message);

}
