package com.matviienko.smart_cook_book.service;

import com.matviienko.smart_cook_book.exception_handling.RecipeNotFoundException;
import com.matviienko.smart_cook_book.repository.Entity.IngredientEntity;
import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import com.matviienko.smart_cook_book.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;

//    public void createRecipe (String name, String description, String note, boolean isfavorite, LocalDate date, Integer time,
//                              String instruction, Integer rate, Integer yield, YieldTypeChoice yield_type,ComplexityLevel complexity) {
//        RecipeEntity recipeEntity = new RecipeEntity();
//        userEntity.setUsername(username);
//        userEntity.setEmail(email);
//        userEntity.setPassword(password);
//        System.out.println(userEntity);
//        userRepository.save(userEntity);

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
                .orElseThrow(RecipeNotFoundException::new);
        recipeEntity.setDate(entity.getDate());
        return recipeRepository.save(recipeEntity);
    }


//    public StudentEntity updateStudent(StudentEntity studentEntity) {
//
//        StudentEntity entity = studentRepository.findById(studentEntity.getId())
//                .orElseThrow(StudentNotFoundException::new);
//
//        if (studentEntity.getName() != null) {
//            entity.setName(studentEntity.getName());
//        }
//
//        if (studentEntity.getEmail() != null) {
//            entity.setEmail(studentEntity.getEmail());
//        }
//
//        if (studentEntity.getPhotos() != null && studentEntity.getPhotos().isEmpty()) {
//            entity.setPhotos(studentEntity.getPhotos());
//        }
//
//        return studentRepository.save(entity);
//    }


    public RecipeEntity findRecipeById(Integer recipe_id) {
        return recipeRepository.findById(recipe_id).orElseThrow(RecipeNotFoundException::new);
    }

    public List<RecipeEntity> findAllRecipe() {
        return recipeRepository.findAll();
    }

//    public void deleteRecipe(Integer id) {
//        recipeRepository.deleteById(id);
//    }

    public void deleteRecipe(Integer recipe_Id) {

        RecipeEntity recipeEntity = recipeRepository.findById(recipe_Id)
                .orElseThrow(RecipeNotFoundException::new);

        recipeRepository.delete(recipeEntity);
    }

    public List<RecipeEntity> getFavouritesRecipe() {
        return recipeRepository.findByIsfavoriteTrue();
    }

}
