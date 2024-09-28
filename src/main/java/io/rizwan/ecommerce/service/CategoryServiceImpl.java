package io.rizwan.ecommerce.service;

import io.rizwan.ecommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category categoryToDelete = categories.stream()
                .filter(category -> category.getCategoryId().equals(categoryId))
                .findFirst().
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categories.remove(categoryToDelete);
        return "Category with categoryId: " + categoryId + " deleted successfully.";
    }

    @Override
    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Optional<Category> optionalCategory = categories.stream()
                .filter(category -> category.getCategoryId().equals(categoryId))
                .findFirst();

        // -- another way to throw the exception, instead of orThrow
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            return existingCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }
}
