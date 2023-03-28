package com.matviienko.smart_cook_book.dto;

import com.matviienko.smart_cook_book.repository.Entity.ENUM.ComplexityLevel;
import com.matviienko.smart_cook_book.repository.Entity.ENUM.YieldType;
import com.matviienko.smart_cook_book.repository.Entity.IngredientEntity;
import com.matviienko.smart_cook_book.repository.Entity.TagEntity;
import lombok.Builder;
import lombok.Data;

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

