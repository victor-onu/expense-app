package com.inits.expenseapp.controllers;

import com.inits.expenseapp.apiresponse.ApiResponse;
import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.models.Expense;
import com.inits.expenseapp.services.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    private ModelMapper modelMapper;

    @Autowired
    public ExpenseController(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @PostMapping("expense")
    public ResponseEntity<ApiResponse<Expense>> addExpense(@Valid @RequestBody ExpenseDto expenseDto){
        Expense newExpense = expenseService.createExpense(expenseDto);
        ApiResponse<Expense> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Expense added successfully");
        response.setData(newExpense);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}