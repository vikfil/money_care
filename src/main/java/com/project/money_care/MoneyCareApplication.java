package com.project.money_care;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.project.money_care.repository"})
public class MoneyCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyCareApplication.class, args);
	}

}
