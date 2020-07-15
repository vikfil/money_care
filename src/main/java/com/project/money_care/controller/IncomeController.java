package com.project.money_care.controller;

import com.project.money_care.dto.IncomesDto;
import com.project.money_care.mapper.IncomesMapper;
import com.project.money_care.model.Incomes;
import com.project.money_care.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/income")
public class IncomeController {

    private final IncomeService incomeService;
    private final IncomesMapper incomesMapper;

    @Autowired
    public IncomeController(IncomeService incomeService, IncomesMapper incomesMapper) {
        this.incomeService = incomeService;
        this.incomesMapper = incomesMapper;
    }


    @GetMapping
    ResponseEntity<List<IncomesDto>> getAllIncomeCategory() {
        List<IncomesDto> incomesDtoList = incomesMapper.toListIncomesDto(incomeService.findAllIncomes());
        return ResponseEntity.ok(incomesDtoList);
    }

    @PostMapping
    ResponseEntity addIncomeCategory(@RequestBody IncomesDto incomesDto ) {
        incomeService.addIncomeCategory(incomesMapper.toIncomes(incomesDto));
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping
    ResponseEntity updateIncomeCategory( @RequestBody IncomesDto categoryWithChange) {
        incomeService.editIncomeCategory(incomesMapper.toIncomes(categoryWithChange));
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    ResponseEntity deleteIncomeCategory(@RequestParam("id") Long id) {
        incomeService.deleteIncomeCategory(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
