package com.project.money_care.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpensiveDto {
    private Long id;
    private String category;
    private int amount;
}
