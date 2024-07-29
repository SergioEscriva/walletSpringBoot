package dev.sergioescriva.wallet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sergioescriva.wallet.models.Category;
import dev.sergioescriva.wallet.services.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public List<Category> getAllCategory() {
        return service.getAllCategory();

    }

    @GetMapping("/{categoryId}")
    public Category getCategoryNameById(@PathVariable Long categoryId) {
        return service.getCategoryNameById(categoryId);

    }

    @PutMapping("/{Id}/{nameNew}")
    public void updateCategory(@PathVariable Long id, @PathVariable Category nameNew) {
        service.updateCategory(id, nameNew);
    }

    @PostMapping("/{name}")
    public void addCategory(@PathVariable String name) {
        service.addCategory(name);
    }

    @DeleteMapping("/{delId}")
    public void delCategoryById(@PathVariable Long delId) {
        service.delCategoryById(delId);

    }
}
