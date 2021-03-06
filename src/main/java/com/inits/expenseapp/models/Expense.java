package com.inits.expenseapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expenses")
public class Expense extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 23, min = 3)
    @NotEmpty(message = "Please enter description")
    private String description;

    @NotBlank
    private String location;

    @Min(value = 0)
    @NotNull(message = "Please enter amount")
    private Double amount;


}
