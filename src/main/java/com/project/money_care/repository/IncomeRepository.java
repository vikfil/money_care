package com.project.money_care.repository;

import com.project.money_care.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findAllByUserId(Long id);
    void deleteByIdAndUserId(Long categoryId, Long authenticatedUserId);
    Optional<Income> findByIdAndUserId(Long categoryId, Long authenticatedUserId);

}
