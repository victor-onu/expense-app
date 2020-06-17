package com.inits.expenseapp.services;

import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.models.Expense;
import com.inits.expenseapp.repositories.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
