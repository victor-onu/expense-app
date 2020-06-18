package com.inits.expenseapp.repositories;

import com.inits.expenseapp.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT sum(amount) FROM Expense")
    Double sumExpenses();
}
