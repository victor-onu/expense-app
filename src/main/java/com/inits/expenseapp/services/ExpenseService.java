package com.inits.expenseapp.services;

import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.models.Expense;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ExpenseService {
    Expense createExpense(ExpenseDto expenseDto);

    Expense viewExpenseById(Long expenseId);

    Collection<Expense> findAllExpenses();

    Expense updateExpenseById(Long expenseId, ExpenseDto expenseDto);

    Expense deleteExpenseById(Long expense_id);
}
