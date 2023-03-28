package com.matviienko.smart_cook_book.controller.rest;

import com.matviienko.smart_cook_book.dto.UserDto;
import com.matviienko.smart_cook_book.dto.mapper.UserMapper;
import com.matviienko.smart_cook_book.exception_handling.UserNotCreateException;
import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import com.matviienko.smart_cook_book.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AccountController {
    @Autowired
    private final UserService userService;

    @DeleteMapping("/{UserId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer UserId) {
        userService.deleteUser(UserId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDto userDto,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreateException(errorMsg.toString());

        }
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);
        UserDto response = UserMapper.INSTANCE.toDto(
                userService.createUser(userEntity));
        return ResponseEntity.ok(HttpStatus.OK);
    }


}