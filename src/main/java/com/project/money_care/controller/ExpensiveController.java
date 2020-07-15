package com.project.money_care.controller;

import com.project.money_care.dto.ExpensiveDto;
import com.project.money_care.mapper.ExpensiveMapper;
import com.project.money_care.model.Expensive;
import com.project.money_care.service.ExpensiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expensive")
public class ExpensiveController {

    private final ExpensiveService expensiveService;
    private final ExpensiveMapper expensiveMapper;

    @Autowired
    public ExpensiveController(ExpensiveService expensiveService, ExpensiveMapper expensiveMapper) {
        this.expensiveService = expensiveService;
        this.expensiveMapper = expensiveMapper;
    }

    @GetMapping
    public ResponseEntity getAllExpensive() {
        List<ExpensiveDto> expensiveDtoList = expensiveMapper.expensiveDtoList(expensiveService.getAllExpensive());
        return new ResponseEntity(expensiveDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addExpensiveCategory(@RequestBody ExpensiveDto newExpensiveDtoCategory) {
        expensiveService.addExpensiveCategory(expensiveMapper.toExpensive(newExpensiveDtoCategory));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateExpensiveCategory(@RequestBody ExpensiveDto expensiveWithChange) {
        expensiveService.editExpensiveCategory(expensiveMapper.toExpensive(expensiveWithChange));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteExpensiveCategory(@RequestParam("id") Long id) {
        expensiveService.deleteExpensiveCategory(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
