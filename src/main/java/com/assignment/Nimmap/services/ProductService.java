package com.assignment.Nimmap.services;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.Nimmap.model.ProductModel;

@Service
public interface ProductService {

    // Get a paginated list of products for a specific category
    Page<ProductModel> getAllProductsByCategory(Long categoryId, Pageable pageable);

    // Save a product - should map ProductModel to Product entity in implementation
    ProductModel saveProduct(ProductModel product);

    // Update a product - should map ProductModel to Product entity in implementation
    ProductModel updateProduct(Long id, ProductModel product);

    // Delete a product by ID
    void deleteProduct(Long id);

	ProductModel getProductById(Long id);

	Page<ProductModel> getAllProducts(Pageable pageable);
}
