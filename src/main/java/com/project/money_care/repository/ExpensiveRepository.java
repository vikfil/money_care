package com.project.money_care.repository;

import com.project.money_care.model.Expensive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensiveRepository extends JpaRepository<Expensive, Long> {
 List<Expensive> findAllByUserId(Long id);
}
