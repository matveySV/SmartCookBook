package com.matviienko.smart_cook_book.controller;


import com.matviienko.smart_cook_book.dto.RecipeDto;
import com.matviienko.smart_cook_book.dto.mapper.RecipeMapper;
import com.matviienko.smart_cook_book.exception_handling.RecipeNotCreateException;
import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import com.matviienko.smart_cook_book.service.RecipeService;
import com.matviienko.smart_cook_book.exception_handling.UserNotCreateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private final RecipeService recipeService;


//    @GetMapping("")
//    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
//        List<RecipeDto> responseDto = RecipeMapper.INSTANCE.toDtoList(
//                recipeService.getAllRecipes());
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeEntity> getRecipeById(@PathVariable Integer recipeId) {
        return new ResponseEntity<>(recipeService.findRecipeById(recipeId), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<HttpStatus> addRecipe(@RequestBody @Valid RecipeDto recipeDto,
                                                BindingResult bindingResult) {
        System.out.println(recipeDto);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new RecipeNotCreateException(errorMsg.toString());

        }
        RecipeEntity recipeEntity = RecipeMapper.INSTANCE.toEntity(recipeDto);
        RecipeDto response = RecipeMapper.INSTANCE.toDto(
                recipeService.createRecipe(recipeEntity));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity updateRecipe(@RequestBody RecipeDto recipeDto) {
        RecipeEntity recipeEntity = RecipeMapper.INSTANCE.toEntity(recipeDto);
        RecipeEntity response = recipeService.updateRecipe(recipeEntity);
        RecipeDto responseUpdateDto = RecipeMapper.INSTANCE.toDto(response);
        return ResponseEntity.ok().body(responseUpdateDto);
    }

    @DeleteMapping("/{recipe_Id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer recipe_Id) {
        recipeService.deleteRecipe(recipe_Id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<RecipeDto>> getRecipes( @RequestParam(defaultValue = "false")  boolean favorites,
                                                       @RequestParam(defaultValue = "1")  int page,
                                                       @RequestParam(defaultValue = "10")  int size) {
        if (favorites){
        List<RecipeDto> responseDto = RecipeMapper.INSTANCE.toDtoList(
                recipeService.getFavouritesRecipe());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);}

        List<RecipeDto> responseDto = RecipeMapper.INSTANCE.toDtoList(
                recipeService.getAllRecipes(page,size));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}



//
//    @PutMapping("/update-recipe")
//    public ResponseEntity<HttpStatus> updateRecipe(@RequestBody RecipeDto recipeDto) {
//        RecipeEntity recipeEntity = RecipeMapper.INSTANCE.toEntity(recipeDto);
//        RecipeDto responseUpdate = RecipeMapper.INSTANCE.toDto(
//                recipeService.updateRecipe(recipeEntity));
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

//    @DeleteMapping("/{recipe_id}")
//    public String deleteRecipe(@PathVariable("recipe_id") Integer recipe_id){
//        Optional<RecipeEntity> recipe = recipeService.findRecipeById(recipe_id);
//        recipe.orElseThrow(()->new NoSuchRecipeException("There is no recipe with ID = " +
//                recipe_id + " in Database"));
//        log.info("RecipeController ->  deleteRecipe -> recipeEntity {}", recipe_id);
//        recipeService.deleteRecipe(recipe_id);
//        return "Recipe with ID = " + recipe_id + " was deleted";
//
//    }

//    @PostMapping("/add-recipe")
//    public RecipeEntity addRecipe(@RequestBody RecipeEntity recipeEntity) {
//        log.info("RecipeController -> addRecipe -> recipeEntity {}", recipeEntity);
//        return recipeService.createRecipe(recipeEntity);
//    }

//    @PutMapping("/update-recipe")
//    public RecipeEntity updateRecipe(@RequestBody RecipeEntity recipeEntity) {
//        return recipeService.updateRecipe(recipeEntity);
//    }

//    @GetMapping("/{recipe_id}")
//    public ResponseEntity<RecipeEntity> findRecipeById(@PathVariable ("id") Integer id) {
//        RecipeEntity recipe = recipeService.findRecipeById(id);
//        return new ResponseEntity<RecipeEntity>(recipe, HttpStatus.OK);
//    }
