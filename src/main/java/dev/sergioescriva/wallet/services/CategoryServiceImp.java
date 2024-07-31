package dev.sergioescriva.wallet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sergioescriva.wallet.models.Category;
import dev.sergioescriva.wallet.repositories.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        Iterable<Category> categories = repository.findAll();
        for (Category category : categories) {
            list.add(category);
        }

        return list;
    }

    @Override
    public Category getCategoryNameById(Long categoryId) {
        Optional<Category> category = repository.findById(categoryId);

        if (category.isPresent()) {
            return category.get();
        }
        return null;
    }

    @Override
    public Category getCategoryIdByName(String categoryName) {

        Iterable<Category> categories = repository.findAll();
        for (Category category : categories) {
            if (category.getCategory().equals(categoryName)) {
                System.out.println("OOOOOOOOO " + category.getId());
                return category;
            }
        }

        return null;

    }

    @Override
    public void updateCategory(Long id, Category nameNew) {
        nameNew.setId(id);
        repository.save(nameNew);
    }

    @Override
    public void addCategory(String name) {
        Category categoryNew = new Category();
        categoryNew.setCategory(name);
        repository.save(categoryNew);
    }

    @Override
    public void delCategoryById(Long delId) {
        repository.deleteById(delId);
    }

}
