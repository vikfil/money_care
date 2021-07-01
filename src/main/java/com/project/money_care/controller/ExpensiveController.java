package com.project.money_care.controller;

import com.project.money_care.dto.ExpensiveDto;
import com.project.money_care.service.ExpensiveService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExpensiveCategory(@PathVariable("id") Long id) {
        expensiveService.deleteExpensiveCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
