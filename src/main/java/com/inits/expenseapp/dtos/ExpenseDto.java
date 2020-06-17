package com.inits.expenseapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {


    @Size(max = 23, min = 3)
    @NotEmpty(message = "Please enter description")
    private String description;

    @NotBlank
    private String location;

    @Min(value = 0)
    @NotNull(message = "Please enter amount")
    private Double amount;
}
