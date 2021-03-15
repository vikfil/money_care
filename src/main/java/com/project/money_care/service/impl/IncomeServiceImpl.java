package com.project.money_care.service.impl;

import com.project.money_care.dto.IncomesDto;
import com.project.money_care.dto.UserDto;
import com.project.money_care.mapper.IncomesMapper;
import com.project.money_care.mapper.UserMapper;
import com.project.money_care.model.Income;
import com.project.money_care.model.User;
import com.project.money_care.repository.IncomeRepository;
import com.project.money_care.service.IncomeService;
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
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final IncomesMapper incomesMapper;
    private final UserMapper userMapper;

    @Override
    public List<IncomesDto> findAllIncomes() {
        long authenticatedUserId = getAuthenticatedUser().getId();
        return incomesMapper.toListIncomesDto(incomeRepository.findAllByUserId(authenticatedUserId));
    }

    @Override
    public void addIncomeCategory(IncomesDto incomesCategory) {
        UserDto authenticatedUserDto = userMapper.userToDto(getAuthenticatedUser());
        incomesCategory.setUser(authenticatedUserDto);
        incomeRepository.save(incomesMapper.toIncomes(incomesCategory));
    }

    @Override
    public void editIncomeCategory(IncomesDto incomesWithChange) {
        long authenticatedUserId = getAuthenticatedUser().getId();
        Optional<Income> incomes = incomeRepository.findByIdAndUserId(incomesWithChange.getId(), authenticatedUserId);
        if (incomes.isEmpty()) {
            throw new RuntimeException("Wrong category to edit");
        }
        IncomesDto incomesDto = incomesMapper.incomesToDto(incomes.get());
        incomesDto.setCategory(incomesWithChange.getCategory());
        incomesDto.setAmount(incomesWithChange.getAmount());
        incomeRepository.save(incomesMapper.toIncomes(incomesDto));
    }

    @Override
    public void deleteIncomeCategory(Long id) {
        long authenticatedUserId = getAuthenticatedUser().getId();
        incomeRepository.deleteByIdAndUserId(id, authenticatedUserId);

    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new RuntimeException("User isn't authenticated");
        }
        return  (User)principal;
    }
}
