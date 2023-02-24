package com.matviienko.smart_cook_book.repository;

import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {
}
