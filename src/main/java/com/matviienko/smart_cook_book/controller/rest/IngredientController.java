package com.matviienko.smart_cook_book.controller.rest;

import com.matviienko.smart_cook_book.dto.RecipeDto;
import com.matviienko.smart_cook_book.dto.IngredientDto;
import com.matviienko.smart_cook_book.dto.mapper.IngredientMapper;
import com.matviienko.smart_cook_book.dto.mapper.RecipeMapper;
import com.matviienko.smart_cook_book.exception_handling.RecipeNotCreateException;
import com.matviienko.smart_cook_book.repository.Entity.IngredientEntity;
import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import com.matviienko.smart_cook_book.service.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe/ingredient")
public class IngredientController {
    @Autowired
    private final IngredientService ingredientService;


    @GetMapping("/")
    public ResponseEntity<List<IngredientDto>> getAllRecipes() {
        List<IngredientDto> responseDto = IngredientMapper.INSTANCE.toDtoList(
                ingredientService.getAllIngredient());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<IngredientEntity> getRecipeById(@PathVariable Integer ingredientId) {
        return new ResponseEntity<>(ingredientService.getIngredientById(ingredientId), HttpStatus.OK);
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Integer ingredientId) {
        ingredientService.deleteIngredientById(ingredientId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> addIngredient(@RequestBody IngredientDto ingredientDto) {
        IngredientEntity ingredientEntity = IngredientMapper.INSTANCE.toEntity(ingredientDto);
        IngredientDto response = IngredientMapper.INSTANCE.toDto(
                ingredientService.addIngredient(ingredientEntity));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity updateIngredient(@RequestBody IngredientDto ingredientDto) {
        IngredientEntity ingredientEntity = IngredientMapper.INSTANCE.toEntity(ingredientDto);
        IngredientEntity response = ingredientService.updateIngredient(ingredientEntity);
        IngredientDto responseUpdateDto = IngredientMapper.INSTANCE.toDto(response);
        return ResponseEntity.ok().body(responseUpdateDto);
    }

}