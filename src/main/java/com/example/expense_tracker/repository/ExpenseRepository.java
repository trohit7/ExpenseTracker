package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense,Integer> {


    @Query("SELECT e.expenseName, e.amount, e.createdDate, e.comments, e.expenseId, c.categoryName FROM Expense e, Category c WHERE c.categoryId = e.categoryId AND e.categoryId = :categoryId")
    public List<Object[]> getExpenseByCategory(int categoryId);

    @Query("SELECT e.expenseName, e.amount, e.createdDate, e.comments, e.expenseId, c.categoryName FROM Expense e, Category c WHERE c.categoryId = e.categoryId AND e.createdDate BETWEEN :fromDate AND :tillDate")
   public List<Object[]> getExpenseByDate(Date fromDate, Date tillDate);


    @Query("SELECT e.expenseName, e.amount, e.createdDate, e.comments, e.expenseId, c.categoryName FROM Expense e, Category c WHERE c.categoryId = e.categoryId AND e.categoryId = :categoryId AND e.createdDate BETWEEN :fromDate AND :tillDate")
    public List<Object[]> getExpenseByCategoryAndDate(Date fromDate, Date tillDate, int categoryId);



}


