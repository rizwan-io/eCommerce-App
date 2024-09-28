package io.rizwan.ecommerce.service;

import io.rizwan.ecommerce.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Long categoryId, Category category);
}
