package com.matviienko.smart_cook_book.dto.mapper;

import com.matviienko.smart_cook_book.dto.RecipeDto;
import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDto toDto(RecipeEntity recipeEntity);
    RecipeEntity toEntity(RecipeDto recipeDto);
    List<RecipeDto> toDtoList(List<RecipeEntity> RecipeEntityList);
    List<RecipeEntity> toEntityList(List<RecipeDto> recipeDtoList);
}