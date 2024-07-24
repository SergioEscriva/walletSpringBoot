package dev.sergioescriva.wallet.services;

import java.util.List;

import dev.sergioescriva.wallet.models.Category;

public interface CategoryService {

    List<Category> getAllCategory();

    void updateCategory(String nameOld, String nameNew);

    void addCategory(String name);

    void delCategoryById(Long delId);
}
