package com.assignment.Nimmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.Nimmap.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
