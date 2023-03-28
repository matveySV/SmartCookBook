package com.matviienko.smart_cook_book.dto;

import com.matviienko.smart_cook_book.repository.Entity.ENUM.UnitChoice;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngredientDto {

    private Integer id;
    private String name;
    private Integer amount;
    private UnitChoice unit;

}

