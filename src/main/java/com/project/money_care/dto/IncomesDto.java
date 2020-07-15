package com.project.money_care.dto;

import com.project.money_care.model.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncomesDto {
    private Long id;
    private String category;
    private int amount;
    //private Users user;

}
