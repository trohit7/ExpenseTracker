package com.example.expense_tracker.service;


import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;





    public List<Category> getCategory(){
        return new ArrayList<>(categoryRepository.findAll());
    }

    public List<String> getOnlyCategories(){

        return categoryRepository.getOnlyCategories();
    }



}
