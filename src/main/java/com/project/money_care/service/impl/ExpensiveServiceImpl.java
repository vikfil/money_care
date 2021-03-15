package com.project.money_care.service.impl;

import com.project.money_care.dto.ExpensiveDto;
import com.project.money_care.dto.UserDto;
import com.project.money_care.mapper.ExpensiveMapper;
import com.project.money_care.mapper.UserMapper;
import com.project.money_care.model.Expensive;
import com.project.money_care.model.User;
import com.project.money_care.repository.ExpensiveRepository;
import com.project.money_care.service.ExpensiveService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ExpensiveServiceImpl implements ExpensiveService {
    private final ExpensiveRepository expensiveRepository;
    private final UserMapper userMapper;
    private final ExpensiveMapper expensiveMapper;

    @Override
    public List<ExpensiveDto> findAllExpensives() {
        long authenticatedUserId = getAuthenticatedUser().getId();
        return expensiveMapper.expensiveDtoList(expensiveRepository.findAllByUserId(authenticatedUserId));
    }

    @Override
    public void addExpensiveCategory(ExpensiveDto newExpensiveCategory) {
        UserDto user = userMapper.userToDto(getAuthenticatedUser());
        newExpensiveCategory.setUser(user);
        expensiveRepository.save(expensiveMapper.toExpensive(newExpensiveCategory));
    }

    @Override
    public void editExpensiveCategory(ExpensiveDto expensiveWithChange) {
        long authenticatedUserId = getAuthenticatedUser().getId();
        Optional<Expensive> expensive = expensiveRepository.findByIdAndUserId(expensiveWithChange.getId(), authenticatedUserId);
        if (expensive.isEmpty()) {
            throw new RuntimeException("Wrong category to edit");
        }
        ExpensiveDto expensiveDto = expensiveMapper.expensiveToDto(expensive.get());
        expensiveDto.setCategory(expensiveWithChange.getCategory());
        expensiveDto.setAmount(expensiveWithChange.getAmount());
       expensiveRepository.save(expensiveMapper.toExpensive(expensiveDto));
    }

    @Override
    public void deleteExpensiveCategory(Long id) {
        long authenticatedUserId = getAuthenticatedUser().getId();
        expensiveRepository.deleteByIdAndUserId(id, authenticatedUserId);
    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new RuntimeException("User isn't authenticated");
        }
        return  (User)principal;
    }
}
