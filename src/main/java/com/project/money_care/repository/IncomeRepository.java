package com.project.money_care.repository;

import com.project.money_care.model.Incomes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Incomes, Long> {
    //@Query("select category, amount from Incomes  inner join Users  on Incomes.user = Users.id where Users.id = ?1")
    List<Incomes> findAllByUserId(Long id);
    void deleteByIdAndUserId(Long categoryId, Long authenticatedUserId);

}
