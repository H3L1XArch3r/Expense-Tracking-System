package com.expensetrackersystem.ExpTrackSys.Controller;

import com.expensetrackersystem.ExpTrackSys.Entity.Expense;
import com.expensetrackersystem.ExpTrackSys.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Get all expense descriptions
    @GetMapping("/expense")
    public List<String> getExpenseDescriptions() {
        return this.expenseService.getExpenseDescriptions();
    }

    // Get all expense amounts
    @GetMapping("/amount")
    public List<Long> getAmounts() {
        return this.expenseService.getAmounts();
    }

    // Add a new expense
    @PostMapping("/expense")
    public Expense addExpense(@RequestBody Expense expense) {
        return this.expenseService.addExpense(expense);
    }

}
