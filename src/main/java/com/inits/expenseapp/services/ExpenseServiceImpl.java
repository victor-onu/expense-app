package com.inits.expenseapp.services;

import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.exceptions.ResourceNotFoundException;
import com.inits.expenseapp.models.Expense;
import com.inits.expenseapp.repositories.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Expense createExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        this.modelMapper.map(expenseDto, expense);
        return expenseRepository.save(expense);
    }

    @Override
    public Expense viewExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId).orElseThrow(
                () -> new ResourceNotFoundException("Expense with such Id does not exist")
        );
    }

    @Override
    public Collection<Expense> findAllExpenses() {
        List<Expense> allExpenses = expenseRepository.findAll();
        if (allExpenses.isEmpty()){
            throw new ResourceNotFoundException("Expense list empty");
        }
        return allExpenses;
    }

    @Override
    public Expense updateExpenseById(Long expenseId, ExpenseDto expenseDto) {
        return expenseRepository.findById(expenseId).map(expense -> {
            expense.setAmount(expenseDto.getAmount());
            expense.setDescription(expenseDto.getDescription());
            expense.setLocation(expenseDto.getLocation());
            return expenseRepository.save(expense);
        }).orElseThrow(() -> new ResourceNotFoundException("Expense with such Id does not Exist"));
    }

    @Override
    public Expense deleteExpenseById(Long expense_id) {
        return expenseRepository.findById(expense_id).map(expense -> {
            expenseRepository.deleteById(expense_id);
            return expense;
        }).orElseThrow(() -> new ResourceNotFoundException("Expense with such Id does not Exist"));
    }
}
