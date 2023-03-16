package com.matviienko.smart_cook_book.repository;

import com.matviienko.smart_cook_book.repository.Entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer>{
}
