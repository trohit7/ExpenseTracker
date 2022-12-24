package com.example.expense_tracker.service;

import com.example.expense_tracker.model.Expense;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addexpense(Expense expense){
        expenseRepository.save(expense);
    }

    public List<Object[]> getbydate(Date fromDate, Date tillDate) {
        return expenseRepository.getExpenseByDate(fromDate, tillDate);

    }

    public List<Object[]> getbyCategory(String category) {
        int categoryId = categoryRepository.getIdForCategory(category);

        return expenseRepository.getExpenseByCategory(categoryId);

    }

    public List<Object[]> getByCategoryAndDate(Date fromDate, Date tillDate, String category){
        int categoryId = categoryRepository.getIdForCategory(category);
        return expenseRepository.getExpenseByCategoryAndDate(fromDate, tillDate, categoryId);
    }

    public void deleteExpenses(int[] ids){
        for (int i : ids) {
            expenseRepository.deleteById(i);
        }
    }
}
