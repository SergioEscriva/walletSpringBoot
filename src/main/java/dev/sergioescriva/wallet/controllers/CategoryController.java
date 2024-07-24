package dev.sergioescriva.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sergioescriva.wallet.repositories.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository repository;

    @GetMapping
    public Iterable<CategoryRepository> getAllCategory() {

        return repository.findAll();
    }

    @PutMapping("//{nameOld}/{nameNew}")

    public void updateCategory(@PathVariable String nameOld, @PathVariable String nameNew) {
        // category = Category().update(name_old, name_new)
        // return category
    }

    @PostMapping("/{name}")
    public void addCategory(@PathVariable String name) {
        // category = Category().new(name)
        // return category
    }

    @DeleteMapping("/{delId}")
    public void delCategoryById(@PathVariable Long delId) {
        // category = Category().delete(del_id)
        // return category

    }
}
