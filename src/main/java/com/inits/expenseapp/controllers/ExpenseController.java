package com.inits.expenseapp.controllers;

import com.inits.expenseapp.apiresponse.ApiResponse;
import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.models.Expense;
import com.inits.expenseapp.services.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

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

    @PostMapping("/expense")
    public ResponseEntity<ApiResponse<Expense>> addExpense(@Valid @RequestBody ExpenseDto expenseDto){
        Expense newExpense = expenseService.createExpense(expenseDto);
        ApiResponse<Expense> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Expense added successfully");
        response.setData(newExpense);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/expense/{expense_id}")
    public ResponseEntity<ApiResponse<Expense>> viewOneExpense(@PathVariable Long expense_id){
        Expense expense = expenseService.viewExpenseById(expense_id);
        ApiResponse<Expense> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Expense retrieved successfully");
        response.setData(expense);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/expense")
    public ResponseEntity<ApiResponse<Collection<Expense>>> getAllExpenses(){
        Collection<Expense> allExpenses = expenseService.findAllExpenses();
        ApiResponse<Collection<Expense>> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("All expenses retrieved successfully");
        response.setData(allExpenses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/expense/{expense_id}")
    public ResponseEntity<ApiResponse<Expense>> updateExpense(@PathVariable Long expense_id, @Valid @RequestBody ExpenseDto expenseDto){
        Expense updatedExpense = expenseService.updateExpenseById(expense_id, expenseDto);
        ApiResponse<Expense> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Expense updated successfully");
        response.setData(updatedExpense);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/expense/{expense_id}")
    public ResponseEntity<ApiResponse<Expense>> deleteExpense(@PathVariable Long expense_id){
        Expense deletedExpense = expenseService.deleteExpenseById(expense_id);
        ApiResponse<Expense> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Expense deleted successfully");
        response.setData(deletedExpense);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/expense/total")
    public ResponseEntity<ApiResponse<Double>> totalExpenses(){
        Double totalExpenses = expenseService.addAllExpenses();
        ApiResponse<Double> response = new ApiResponse<>(HttpStatus.OK);
        response.setMessage("Expense added successfully: Total expenses is " +totalExpenses);
        response.setData(totalExpenses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
