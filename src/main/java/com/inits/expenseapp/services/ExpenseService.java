package com.inits.expenseapp.services;

import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.models.Expense;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {
    Expense createExpense(ExpenseDto expenseDto);

    Expense viewExpenseById(Long expenseId);
}
