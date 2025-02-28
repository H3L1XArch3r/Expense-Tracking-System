package com.expensetrackersystem.ExpTrackSys.Dao;

import com.expensetrackersystem.ExpTrackSys.Entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExpenseDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExpenseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add an expense
    public int addExpense(Expense expense) {
        String query = "INSERT INTO expense (expense, amount) VALUES (?, ?)";
        return jdbcTemplate.update(query, expense.getExpense(), expense.getAmount());
    }

    // Get all expenses
    public List<Expense> getAllExpenses() {
        String query = "SELECT id, expense, amount FROM expense";
        return jdbcTemplate.query(query, this::mapRowToExpense);
    }

    // Get an expense by ID
    public Expense getExpenseById(Long id) {
        String query = "SELECT id, expense, amount FROM expense WHERE id = ?";
        return jdbcTemplate.queryForObject(query, this::mapRowToExpense, id);
    }

    // Update an expense
    public int updateExpense(Expense expense) {
        String query = "UPDATE expense SET expense = ?, amount = ? WHERE id = ?";
        return jdbcTemplate.update(query, expense.getExpense(), expense.getAmount(), expense.getId());
    }

    // Delete an expense by ID
    public int deleteExpense(Long id) {
        String query = "DELETE FROM expense WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    // RowMapper for Expense
    private Expense mapRowToExpense(ResultSet rs, int rowNum) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getLong("id"));
        expense.setExpense(rs.getString("expense"));
        expense.setAmount(rs.getInt("amount"));
        return expense;
    }
}
