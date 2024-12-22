package com.assignment.Nimmap.NimmapserviceImpl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.Nimmap.entities.Category;
import com.assignment.Nimmap.model.CategoryModel;
import com.assignment.Nimmap.repositories.CategoryRepository;
import com.assignment.Nimmap.services.CategoryService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CategoryModel> getAllCategories(Pageable pageable) {
        LOGGER.info("CategoryServiceImpl >>> getAllCategories() ==== start");

        Page<Category> categories = categoryRepository.findAll(pageable); 
        if (categories.isEmpty()) {
            LOGGER.info("No categories found.");
            return Page.empty(); 
        }

        Page<CategoryModel> categoryModels = categories.map(category -> modelMapper.map(category, CategoryModel.class));

        LOGGER.info("CategoryServiceImpl >>> getAllCategories() ==== end");
        return categoryModels;
    }


    @Override
    public void deleteCategory(Long id) {
        LOGGER.info("CategoryServiceImpl >>> deleteCategory() ==== start");

        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with Id: " + id);
        }
        categoryRepository.deleteById(id);
        LOGGER.info("Category deleted successfully with ID: " + id);

        LOGGER.info("CategoryServiceImpl >>> deleteCategory() ==== end");
    }

    @Override
    public CategoryModel saveCategory(CategoryModel category) {
        LOGGER.info("CategoryServiceImpl >>> saveCategory() ==== start");

        Category categoryEntity = modelMapper.map(category, Category.class);
        Category savedEntity = categoryRepository.save(categoryEntity);
        CategoryModel savedModel = modelMapper.map(savedEntity, CategoryModel.class);

        LOGGER.info("CategoryServiceImpl >>> saveCategory() ==== end");
        return savedModel;
    }

    @Override
    public CategoryModel updateCategory(CategoryModel updatedCategory, Long id) {
        LOGGER.info("CategoryServiceImpl >>> updateCategory() ==== start");

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with Id: " + id));

        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());

        Category updatedEntity = categoryRepository.save(category);
        CategoryModel updatedModel = modelMapper.map(updatedEntity, CategoryModel.class);

        LOGGER.info("CategoryServiceImpl >>> updateCategory() ==== end");
        return updatedModel;
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        LOGGER.info("CategoryServiceImpl >>> getCategoryById() ==== start");

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with Id: " + id));
        CategoryModel categoryModel = modelMapper.map(category, CategoryModel.class);

        LOGGER.info("CategoryServiceImpl >>> getCategoryById() ==== end");
        return categoryModel;
    }
}
