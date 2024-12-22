package com.assignment.Nimmap.NimmapserviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.Nimmap.entities.Category;
import com.assignment.Nimmap.entities.Product;
import com.assignment.Nimmap.model.ProductModel;
import com.assignment.Nimmap.repositories.CategoryRepository;
import com.assignment.Nimmap.repositories.ProductRepository;
import com.assignment.Nimmap.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<ProductModel> getAllProductsByCategory(Long categoryId, Pageable pageable) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found");
        }
        return products.map(product -> modelMapper.map(product, ProductModel.class));
    }

    @Override
    public ProductModel saveProduct(ProductModel productModel) {
    	 Category category = categoryRepository.findById(productModel.getCategoryId())
                 .orElseThrow(() -> new EntityNotFoundException("Category not found with Id: " + productModel.getCategoryId()));
        Product product = modelMapper.map(productModel, Product.class);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductModel.class);
    }

    @Override
    public ProductModel updateProduct(Long id, ProductModel productModel) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setPrice(productModel.getPrice());
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductModel.class);
    }
    
    @Override
    public ProductModel getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));  
        return modelMapper.map(product, ProductModel.class);
    }
    @Override
    public Page<ProductModel> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable); 
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found");
        }
        return products.map(product -> modelMapper.map(product, ProductModel.class));
    }



    @Override
    public void deleteProduct(Long id) {
    	 productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));  
        productRepository.deleteById(id);
    }
}
