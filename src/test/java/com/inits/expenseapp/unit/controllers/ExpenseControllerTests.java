package com.inits.expenseapp.unit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inits.expenseapp.controllers.ExpenseController;
import com.inits.expenseapp.dtos.ExpenseDto;
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

import static com.inits.expenseapp.utils.TestUtils.asJsonString;
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

}
