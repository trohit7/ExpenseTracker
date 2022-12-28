package com.example.expense_tracker.controller;



import com.example.expense_tracker.model.Category;
import com.example.expense_tracker.repository.CategoryRepository;

import com.example.expense_tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;







    @GetMapping("/all")
    public  List<Category> getCategory(){
        return categoryService.getCategory();
    }

    @GetMapping("/CategoryList")
    public List<String> getOneCategory(){
        return categoryService.getOnlyCategories();
    }
}
