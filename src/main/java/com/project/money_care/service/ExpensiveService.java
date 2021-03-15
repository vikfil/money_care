package com.project.money_care.service;

import com.project.money_care.dto.ExpensiveDto;
import java.util.List;

public interface ExpensiveService {

    List<ExpensiveDto> findAllExpensives();
    void addExpensiveCategory(ExpensiveDto newExpensive);
    void editExpensiveCategory(ExpensiveDto expensiveWithChange);
    void deleteExpensiveCategory(Long id);
}
