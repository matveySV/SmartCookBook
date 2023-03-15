package com.matviienko.smart_cook_book.dto.mapper;

import com.matviienko.smart_cook_book.dto.UserDto;
import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<UserEntity> userEntityList);
    List<UserEntity> toEntityList(List<UserDto> userDtoList);
}