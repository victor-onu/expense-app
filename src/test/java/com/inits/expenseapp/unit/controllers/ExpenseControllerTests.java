package com.inits.expenseapp.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inits.expenseapp.controllers.ExpenseController;
import com.inits.expenseapp.dtos.ExpenseDto;
import com.inits.expenseapp.exceptions.ResourceNotFoundException;
import com.inits.expenseapp.models.Expense;
import com.inits.expenseapp.services.ExpenseService;
import com.inits.expenseapp.utils.TestModels;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.inits.expenseapp.utils.TestUtils.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ExpenseController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ExpenseControllerTests {

    private String path = "/api/expense";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ExpenseService expenseService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    void addExpense_ShouldAddSuccessfully() throws Exception{
        ExpenseDto expenseDto = TestModels.createExpenseDto();

        mockMvc.perform(MockMvcRequestBuilders.post(path).contentType("application/json").content(asJsonString(expenseDto)))
        .andExpect(status().isCreated())
                .andExpect(jsonPath(".message").value("Expense added successfully"));
    }

    @Test
    void viewExpense_ShouldLoadExpenseDetailsSuccessfully() throws Exception{
        Long expenseId = 1L;
        Expense expense = new Expense(expenseId, "food", "Lagos", 5.5);
        given(expenseService.viewExpenseById(anyLong())).willReturn(expense);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/expense/{expense_id}", expenseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.location", is(expense.getLocation())));
    }

    @Test
    void viewExpense_ShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() throws Exception{
        Long expenseId = 1L;
        given(expenseService.viewExpenseById(anyLong())).willThrow(new ResourceNotFoundException("Expense with such Id does not exist"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/expense/{expense_id}", expenseId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Expense with such Id does not exist"));
    }

    @Test
    void getAllExpenses_ShouldReturnAllExpenses() throws Exception{
        Expense expense1 = new Expense(1L, "food", "Lagos", 5.5);
        Expense expense2 = new Expense(2L, "fuel", "Lagos", 3.5);

        List<Expense> expenseList = new ArrayList<>(Arrays.asList(expense1, expense2));

        given(expenseService.findAllExpenses()).willReturn(expenseList);

        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".message").value("All expenses retrieved successfully"))
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @Test
    void getAllExpenses_WhenEmpty() throws Exception{
        given(expenseService.findAllExpenses()).willThrow(new ResourceNotFoundException("Expense list empty"));

        mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateExpense_ShouldUpdateSuccessfully() throws Exception{
        ExpenseDto expenseDto = TestModels.createExpenseDto();
        Long expenseId = 1L;

        given(expenseService.updateExpenseById(expenseId, expenseDto))
                .willReturn(new Expense(expenseId, "food", "Lagos", 5.5));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/expense/{expense_id}", expenseId).contentType("application/json").content(asJsonString(expenseDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".message").value("Expense updated successfully"));
    }

    @Test
    void updateExpense_ShouldThrowExceptionWhenIdDoesNotExist() throws Exception{
        ExpenseDto expenseDto = TestModels.createExpenseDto();
        Long expenseId = 1L;

        given(expenseService.updateExpenseById(expenseId, expenseDto))
                .willThrow(new ResourceNotFoundException("Expense with such Id does not Exist"));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/expense/{expense_id}", expenseId).contentType("application/json").content(asJsonString(expenseDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteExpense_ShouldDeleteSuccessfully() throws Exception{
        Long expenseId = 1L;
        Expense expense = new Expense(expenseId, "food", "Lagos", 5.5);

        given(expenseService.deleteExpenseById(expenseId)).willReturn(expense);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/expense/{expense_id}", expenseId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteExpense_ShouldThrowExceptionWhenIdDoesNotExist() throws Exception{
        given(expenseService.deleteExpenseById(anyLong())).willThrow(new ResourceNotFoundException("Expense with such Id does not exist"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/expense/{expense_id}", anyLong()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Expense with such Id does not exist"));
    }

}
