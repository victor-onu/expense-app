package com.inits.expenseapp.utils;

import com.inits.expenseapp.dtos.ExpenseDto;

public class TestModels {
    public static ExpenseDto createExpenseDto(){
        ExpenseDto createExpenseRequest = new ExpenseDto("Bought food", "Lagos", 3.0);
        return createExpenseRequest;
    }
}
