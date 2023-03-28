package com.matviienko.smart_cook_book.service;

import com.matviienko.smart_cook_book.exception_handling.DataNotFoundException;
import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import com.matviienko.smart_cook_book.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public RecipeEntity createRecipe(RecipeEntity recipeEntity) {
        recipeEntity.setUserId(userService.getCurrentUserId());
        recipeEntity.setDate(LocalDate.now());
        return recipeRepository.saveAndFlush(recipeEntity);
    }

    public List<RecipeEntity> getAllRecipes(int page, int size) {
        return recipeRepository.findAll(PageRequest.of(page - 1, size)).getContent();
    }


    public RecipeEntity updateRecipe(RecipeEntity recipeEntity) {
        RecipeEntity entity = recipeRepository.findById(recipeEntity.getRecipe_id())
                .orElseThrow(DataNotFoundException::new);
        recipeEntity.setDate(entity.getDate());
        return recipeRepository.save(recipeEntity);
    }


    public RecipeEntity findRecipeById(Integer recipe_id) {
        return recipeRepository.findById(recipe_id).orElseThrow(DataNotFoundException::new);
    }

    public List<RecipeEntity> findAllRecipe() {
        return recipeRepository.findAll();
    }

    public void deleteRecipe(Integer recipe_Id) {

        RecipeEntity recipeEntity = recipeRepository.findById(recipe_Id)
                .orElseThrow(DataNotFoundException::new);

        recipeRepository.delete(recipeEntity);
    }

    public List<RecipeEntity> getFavouritesRecipe() {
        System.out.println("favorite is true");
        return recipeRepository.findByIsfavoriteTrue();
    }

}
