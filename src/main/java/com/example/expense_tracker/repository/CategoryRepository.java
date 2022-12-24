package com.example.expense_tracker.repository;

import com.example.expense_tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    @Query("select c.categoryId from Category c where c.categoryName = :category")
   public int getIdForCategory(@Param("category") String category);


    public Category findByCategoryName(String category);




    @Query("select c.categoryName from Category c")
    public List<String> getOnlyCategories();


}
