package com.project.money_care.repository;

import com.project.money_care.model.Expensive;
import com.project.money_care.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

//@Repository
public interface ExpensiveRepository extends JpaRepository<Expensive, Long> {
 List<Expensive> findAllByUserId(Long id);
 void deleteByIdAndUserId(Long categoryId, Long authenticatedUserId);
 Optional<Expensive> findByIdAndUserId(Long categoryId, Long authenticatedUserId);

}
