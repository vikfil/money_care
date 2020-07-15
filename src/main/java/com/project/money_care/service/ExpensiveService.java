package com.project.money_care.service;

import com.project.money_care.model.Expensive;

import java.util.List;

public interface ExpensiveService {
    List<Expensive> getAllExpensive();
    void addExpensiveCategory(Expensive newExpensive);
    void editExpensiveCategory(Expensive expensiveWithChange);
    void deleteExpensiveCategory(Long id);
}
