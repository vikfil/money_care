package com.project.money_care.mapper;

import com.project.money_care.dto.IncomesDto;
import com.project.money_care.model.Incomes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncomesMapper {
    List<IncomesDto> toListIncomesDto(List<Incomes> incomesList);
    Incomes toIncomes(IncomesDto incomesDto);
    IncomesDto incomesToDto(Incomes incomes);
}
