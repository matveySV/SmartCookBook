package com.matviienko.smart_cook_book.dto;

import com.matviienko.smart_cook_book.repository.Entity.ENUM.ComplexityLevel;
import com.matviienko.smart_cook_book.repository.Entity.ENUM.YieldType;
import com.matviienko.smart_cook_book.repository.Entity.IngredientEntity;
import com.matviienko.smart_cook_book.repository.Entity.TagEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Data
public class RecipeDto {

    private Integer recipe_id;
    private String name;
    private String description;
    private String note;
    private boolean isfavorite;
    private Integer time;
    private String instruction;
    private Integer rate;
    private Integer yield;
    private YieldType yieldType;
    private ComplexityLevel complexityLevel;
    private Set<IngredientEntity> ingredients;
    private Set<TagEntity> tags;
}

//    private Integer userId;
//    @NotEmpty(message = "Username should not be empty")
//    @Size(min=2, max=50, message = "Username should be between 2 and 50 characters")
//    private String username;
//    @Email
//    @NotEmpty(message = "Email should not be empty")
//    private String email;
//    @Size(min = 3, max = 20, message = "Password size must 3-20 symbols")
//    private String password;
////@Min(value = 0, message = "Age should be greater than 0") - for numbers!!!