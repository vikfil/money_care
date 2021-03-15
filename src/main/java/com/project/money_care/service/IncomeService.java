package com.project.money_care.service;

import com.project.money_care.dto.IncomesDto;

import java.util.List;

public interface IncomeService {

    List<IncomesDto> findAllIncomes();
    void addIncomeCategory(IncomesDto incomesName);
    void editIncomeCategory(IncomesDto categoryWithChanges);
    void deleteIncomeCategory(Long  id);
}
