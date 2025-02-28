package com.expensetrackersystem.ExpTrackSys.Entity;

import jakarta.persistence.*;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated primary key

    @Column(nullable = false)
    private String expense; // Expense description

    @Column(nullable = false)
    private Integer amount; // Expense amount

    // Default constructor (required by JPA)
    public Expense() {
    }

    // Custom constructor (optional)
    public Expense(String expense, Integer amount) {
        this.expense = expense;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    // Override toString for debugging
    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expense='" + expense + '\'' +
                ", amount=" + amount +
                '}';
    }

    // Override equals and hashCode for object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense1 = (Expense) o;

        return id != null ? id.equals(expense1.id) : expense1.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
