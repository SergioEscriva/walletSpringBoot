package dev.sergioescriva.wallet.services;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(list);
        return list;
    }

    @Override
    public void updateCategory(Long id, Category nameNew) {
        nameNew.setId(id);
        repository.save(nameNew);
    }

    @Override
    public void addCategory(Category name) {
        repository.save(name);
    }

    @Override
    public void delCategoryById(Long delId) {
        repository.deleteById(delId);
    }

}
