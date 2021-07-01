package com.project.money_care.controller;

import com.project.money_care.dto.IncomesDto;
import com.project.money_care.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping(value = "income")
@AllArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @GetMapping
    public ResponseEntity<List<IncomesDto>> getAllIncomeCategory() {
        List<IncomesDto> incomesDtoList = incomeService.findAllIncomes();
        return ResponseEntity.ok(incomesDtoList);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addIncomeCategory(@RequestBody IncomesDto incomesDto ) {
        incomeService.addIncomeCategory(incomesDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<HttpStatus> updateIncomeCategory( @RequestBody IncomesDto categoryWithChange) {
        incomeService.editIncomeCategory(categoryWithChange);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteIncomeCategory(@PathVariable Long id) {
        incomeService.deleteIncomeCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
