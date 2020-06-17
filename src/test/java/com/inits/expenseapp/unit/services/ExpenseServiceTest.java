package com.inits.expenseapp.unit.services;

import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.exceptions.ResourceNotFoundException;
import com.inits.expenseapp.models.Expense;
import com.inits.expenseapp.repositories.ExpenseRepository;
import com.inits.expenseapp.services.ExpenseServiceImpl;
import com.inits.expenseapp.utils.TestModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private Expense expense;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Test
    void addDepartment_ShouldAddSuccessfully() throws Exception{
        final ExpenseDto expenseDto = TestModels.createExpenseDto();

        given(expenseRepository.save(any(Expense.class))).willAnswer(invocation ->  invocation.getArgument(0));

        expenseService.createExpense(expenseDto);
        verify(expenseRepository).save(any(Expense.class));
    }

    @Test
    void viewExpenseById_ShouldReturnExpense() throws Exception{
        Long expenseId = 1L;
        Expense expense = new Expense(expenseId, "food", "Lagos", 5.5);

        given(expenseRepository.findById(anyLong())).willReturn(java.util.Optional.of(expense));
        Expense foundExpense = expenseService.viewExpenseById(expenseId);

        assertEquals(foundExpense, expense);
    }

    @Test
    void viewExpenseById_ShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() throws Exception{
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            expenseService.viewExpenseById(anyLong());
        });

        String expectedError = "Expense with such Id does not exist";
        String actualMessage = exception.getLocalizedMessage();
        assertTrue(actualMessage.contains(expectedError));
    }

    @Test
    void findAllExpenses_ShouldReturnAllExpenses(){
        Expense expense1 = new Expense(1L, "food", "Lagos", 5.5);
        Expense expense2 = new Expense(2L, "fuel", "Lagos", 3.5);

        List<Expense> expenseList = new ArrayList<>(Arrays.asList(expense1, expense2));

        given(expenseRepository.findAll()).willReturn(expenseList);

        Collection<Expense> expenses = expenseService.findAllExpenses();

        assertEquals(expenseList, expenses);
    }

    @Test
    void findAllExpenses_WhenExpensesEmpty() throws Exception{
        List<Expense> emptyExpenseList = new ArrayList<>();
        when(expenseRepository.findAll()).thenReturn(emptyExpenseList);
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            expenseService.findAllExpenses();
        });

        String expectedMessage = "Expense list empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateExpense_ShouldUpdateSuccessfully() throws Exception{
        ExpenseDto expenseDto = TestModels.createExpenseDto();
        Long expenseId = 1L;
        Expense expense = new Expense(expenseId, "food", "Lagos", 5.5);

        given(expenseRepository.findById(expenseId)).willReturn(java.util.Optional.of(expense));
        given(expenseRepository.save(expense)).willReturn(expense);

        Expense updatedExpense = expenseService.updateExpenseById(expense.getId(), expenseDto);

        assertThat(updatedExpense).isNotNull();
        verify(expenseRepository).save(any(Expense.class));
    }

    @Test
    void updateExpense_ShouldThrowResourceNotFoundWhenIdDoesNotExist() throws Exception{
        ExpenseDto expenseDto = TestModels.createExpenseDto();
        Long expenseId = 1L;
        Expense expense = new Expense(expenseId, "food", "Lagos", 5.5);

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            expenseService.updateExpenseById(anyLong(), expenseDto);
        });
        String expectedError = "Expense with such Id does not Exist";
        String actualMessage = exception.getLocalizedMessage();
        assertTrue(actualMessage.contains(expectedError));
    }





}
