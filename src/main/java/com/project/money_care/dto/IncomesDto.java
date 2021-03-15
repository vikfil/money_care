package com.project.money_care.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncomesDto {
    private Long id;
    private String category;
    private double amount;
    private UserDto user;

}
