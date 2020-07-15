package com.project.money_care.service;

import com.project.money_care.model.Incomes;
import java.util.List;

public interface IncomeService {
    List<Incomes> findAllIncomes();
    void addIncomeCategory(Incomes incomesName);
    void editIncomeCategory(Incomes categoryWithChanges);
    void deleteIncomeCategory(Long  id);
}
