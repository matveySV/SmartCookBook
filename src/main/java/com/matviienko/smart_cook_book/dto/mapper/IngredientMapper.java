package com.matviienko.smart_cook_book.dto.mapper;

import com.matviienko.smart_cook_book.dto.IngredientDto;
import com.matviienko.smart_cook_book.repository.Entity.IngredientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDto toDto(IngredientEntity ingredientEntity);
    IngredientEntity toEntity(IngredientDto ingredientDto);
    List<IngredientDto> toDtoList(List<IngredientEntity> IngredientEntityList);
    List<IngredientEntity> toEntityList(List<IngredientDto> ingredientDtoList);
}
