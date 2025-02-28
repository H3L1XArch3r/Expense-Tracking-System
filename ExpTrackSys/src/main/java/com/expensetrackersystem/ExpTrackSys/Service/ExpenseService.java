package com.expensetrackersystem.ExpTrackSys.Service;

import com.expensetrackersystem.ExpTrackSys.Entity.Expense;

import java.util.List;

public interface ExpenseService {

    // Create the "expense" table
    void createTable();

    // Fetch all expenses
    List<Expense> getExpenses();

    // Fetch a specific expense by ID
    Expense getExpense(long id);

    // Fetch all expense descriptions
    List<String> getExpenseDescriptions();

    // Fetch all expense amounts
    List<Long> getAmounts();

    // Add a new expense
    Expense addExpense(Expense expense);
}
