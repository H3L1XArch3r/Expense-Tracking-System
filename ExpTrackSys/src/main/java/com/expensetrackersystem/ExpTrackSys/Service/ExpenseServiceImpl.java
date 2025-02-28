package com.expensetrackersystem.ExpTrackSys.Service;

import com.expensetrackersystem.ExpTrackSys.Entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExpenseServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create table if it doesn't exist
    @Override
    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS expense (" +
                "id SERIAL PRIMARY KEY, " +
                "expense VARCHAR(100) NOT NULL, " +
                "amount INTEGER NOT NULL)";
        try {
            jdbcTemplate.execute(query);
            System.out.println("Table created successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Error creating table: " + e.getMessage());
        }
    }

    // Fetch all expenses
    @Override
    public List<Expense> getExpenses() {
        String query = "SELECT id, expense, amount FROM expense";
        try {
            return jdbcTemplate.query(query, (rs, rowNum) -> {
                Expense expense = new Expense();
                expense.setId(rs.getLong("id"));
                expense.setExpense(rs.getString("expense"));
                expense.setAmount(rs.getInt("amount"));
                return expense;
            });
        } catch (Exception e) {
            throw new RuntimeException("Error fetching expenses: " + e.getMessage());
        }
    }

    // Add a new expense
    @Override
    public Expense addExpense(Expense expense) {
        String query = "INSERT INTO expense (expense, amount) VALUES (?, ?)";
        try {
            jdbcTemplate.update(query, expense.getExpense(), expense.getAmount());
            System.out.println("Expense added successfully: " + expense);
            return expense;
        } catch (Exception e) {
            throw new RuntimeException("Error adding expense: " + e.getMessage());
        }
    }

    // Fetch a single expense by ID
    @Override
    public Expense getExpense(long id) {
        String query = "SELECT id, expense, amount FROM expense WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                Expense expense = new Expense();
                expense.setId(rs.getLong("id"));
                expense.setExpense(rs.getString("expense"));
                expense.setAmount(rs.getInt("amount"));
                return expense;
            }, id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching expense with ID " + id + ": " + e.getMessage());
        }
    }

    // Fetch all expense descriptions
    @Override
    public List<String> getExpenseDescriptions() {
        String query = "SELECT expense FROM expense";
        try {
            return jdbcTemplate.query(query, (rs, rowNum) -> rs.getString("expense"));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching expense descriptions: " + e.getMessage());
        }
    }

    // Fetch all amounts
    @Override
    public List<Long> getAmounts() {
        String query = "SELECT amount FROM expense";
        try {
            return jdbcTemplate.query(query, (rs, rowNum) -> rs.getLong("amount"));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching amounts: " + e.getMessage());
        }
    }
}
