package com.project.money_care.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties")
@ComponentScan(basePackages = {"com.project.money_care.service"})
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.transport.protocol}")
    private String mailTransportProtocol;

    @Value("${spring.mail.smtp.auth}")
    private String mailSmtpAuth;

    @Value("${spring.mail.smtp.starttls.enable}")
    private String mailSmtpStarttlsEnable;

    @Value("${spring.mail.smtp.socketFactory.port}")
    private String mailSmtpSocketFactoryPort;

    @Value("${spring.mail.smtp.socketFactory.class}")
    private String mailSmtpSocketFactoryClass;

    @Value("${spring.mail.smtp.socketFactory.fallback}")
    private String mailSmtpSocketFactoryFallback;

    @Value("${spring.mail.debug}")
    private String mailDebug;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", mailTransportProtocol);
        properties.setProperty("mail.smtp.auth", mailSmtpAuth);
        properties.setProperty("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        properties.setProperty("mail.smtp.socketFactory.port", mailSmtpSocketFactoryPort);
        properties.setProperty("mail.smtp.socketFactory.class", mailSmtpSocketFactoryClass);
        properties.setProperty("mail.smtp.socketFactory.fallback", mailSmtpSocketFactoryFallback);
        properties.setProperty("mail.debug", mailDebug);
        return properties;
    }
}
