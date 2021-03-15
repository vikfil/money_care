package com.project.money_care.service.impl;

import com.project.money_care.model.Expensive;
import com.project.money_care.model.User;
import com.project.money_care.repository.ExpensiveRepository;
import com.project.money_care.service.ExpensiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpensiveServiceImpl implements ExpensiveService {
    private final ExpensiveRepository expensiveRepository;

    @Autowired
    public ExpensiveServiceImpl(ExpensiveRepository expensiveRepository) {
        this.expensiveRepository = expensiveRepository;
    }

    @Override
    public List<Expensive> getAllExpensive() {
        long authenticatedUserId = getAuthenticatedUser().getId();
        return expensiveRepository.findAllByUserId(authenticatedUserId);
    }

    @Override
    public void addExpensiveCategory(Expensive newExpensiveCategory) {

            newExpensiveCategory.setUser(getAuthenticatedUser());
        expensiveRepository.save(newExpensiveCategory);
    }

    @Override
    public void editExpensiveCategory(Expensive expensiveWithChange) {
      // Expensive oldExpensive = expensiveRepository.findById(expensiveWithChange.getId()).get();
       expensiveRepository.save(expensiveWithChange);

    }

    @Override
    public void deleteExpensiveCategory(Long id) {
        expensiveRepository.deleteById(id);

    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new RuntimeException("User isn't authenticated");
        }
        return  (User)principal;
    }
}
