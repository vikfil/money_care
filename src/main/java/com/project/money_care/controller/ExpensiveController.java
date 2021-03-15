package com.project.money_care.controller;

import com.project.money_care.dto.ExpensiveDto;
import com.project.money_care.service.ExpensiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/expensive")
@AllArgsConstructor
public class ExpensiveController {

    private final ExpensiveService expensiveService;

    @GetMapping
    public ResponseEntity<List<ExpensiveDto>> getAllExpensive() {
        List<ExpensiveDto> expensiveDtoList = expensiveService.findAllExpensives();
        return new ResponseEntity<>(expensiveDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addExpensiveCategory(@RequestBody ExpensiveDto newExpensiveCategory) {
        expensiveService.addExpensiveCategory(newExpensiveCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateExpensiveCategory(@RequestBody ExpensiveDto expensiveWithChange) {
        expensiveService.editExpensiveCategory(expensiveWithChange);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteExpensiveCategory(@RequestParam("id") Long id) {
        expensiveService.deleteExpensiveCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
