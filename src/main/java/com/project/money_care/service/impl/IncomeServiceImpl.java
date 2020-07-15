package com.project.money_care.service.impl;

import com.project.money_care.model.Incomes;
import com.project.money_care.model.MyUserDetails;
import com.project.money_care.model.Users;
import com.project.money_care.repository.IncomeRepository;
import com.project.money_care.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<Incomes> findAllIncomes() {
        long authenticatedUserId = 0;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            authenticatedUserId = ((MyUserDetails)principal).getUser().getId();
        }
        return incomeRepository.findAllByUserId(authenticatedUserId);
    }

    @Override
    public void addIncomeCategory(Incomes incomesName) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            Users user = ((MyUserDetails)principal).getUser();
            incomesName.setUser(user);
        }
        incomeRepository.save(incomesName);

    }

    @Override
    public void editIncomeCategory(Incomes categoryWithChange) {
        Incomes incomes = incomeRepository.findById(categoryWithChange.getId()).get();
        incomes.setCategory(categoryWithChange.getCategory());
        incomes.setAmount(categoryWithChange.getAmount());
        incomeRepository.save(incomes);
    }

    @Override
    public void deleteIncomeCategory(Long id) {
        long authenticatedUserId = 0;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            authenticatedUserId = ((MyUserDetails)principal).getUser().getId();
        }
        incomeRepository.deleteByIdAndUserId(id, authenticatedUserId);

    }
//    private Users getAuthenticatedUser() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            return ((MyUserDetails) principal).getUser();
//        }
//        return null;
//     }

}
