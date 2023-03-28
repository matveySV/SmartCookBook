package com.matviienko.smart_cook_book.service;

import com.matviienko.smart_cook_book.exception_handling.DataNotFoundException;
import com.matviienko.smart_cook_book.repository.Entity.IngredientEntity;
import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import com.matviienko.smart_cook_book.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientEntity addIngredient(IngredientEntity ingredientEntity) {
        return ingredientRepository.save(ingredientEntity);
    }

    public List<IngredientEntity> getAllIngredient() {
        return ingredientRepository.findAll();
    }

    public IngredientEntity getIngredientById(int ingredientId) {
        return ingredientRepository.findById(ingredientId).orElseThrow(DataNotFoundException::new);
    }

    public void deleteIngredientById(int ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    public IngredientEntity updateIngredient(IngredientEntity ingredientEntity) {
        IngredientEntity ingredient = ingredientRepository.findById(ingredientEntity.getId())
                .orElseThrow(DataNotFoundException::new);
        return ingredientRepository.save(ingredientEntity);
    }

}
