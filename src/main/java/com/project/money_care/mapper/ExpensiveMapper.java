package com.project.money_care.mapper;

import com.project.money_care.dto.ExpensiveDto;
import com.project.money_care.model.Expensive;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpensiveMapper {
    List<ExpensiveDto> expensiveDtoList(List<Expensive> expensiveList);
    ExpensiveDto expensiveToDto(Expensive expensive);
    Expensive toExpensive(ExpensiveDto expensiveDto);
}
