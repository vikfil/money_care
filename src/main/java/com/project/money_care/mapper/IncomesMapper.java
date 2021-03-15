package com.project.money_care.mapper;

import com.project.money_care.dto.IncomesDto;
import com.project.money_care.model.Income;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncomesMapper {
    List<IncomesDto> toListIncomesDto(List<Income> incomeList);
    Income toIncomes(IncomesDto incomesDto);
    IncomesDto incomesToDto(Income income);
}
