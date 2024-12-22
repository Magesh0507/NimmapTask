package com.assignment.Nimmap.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.Nimmap.model.CategoryModel;

@Service
public interface CategoryService {

    Page<CategoryModel> getAllCategories(Pageable pageable);

    CategoryModel getCategoryById(Long id);

    CategoryModel saveCategory(CategoryModel category);

    CategoryModel updateCategory(CategoryModel updatedCategory, Long id);

    void deleteCategory(Long id);
}
