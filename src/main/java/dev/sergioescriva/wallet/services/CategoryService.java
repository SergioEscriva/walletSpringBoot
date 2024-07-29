package dev.sergioescriva.wallet.services;

import java.util.List;

import dev.sergioescriva.wallet.models.Category;

public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategoryNameById(Long categoryId);

    void updateCategory(Long id, Category nameNew);

    void addCategory(String name);

    void delCategoryById(Long delId);
}
