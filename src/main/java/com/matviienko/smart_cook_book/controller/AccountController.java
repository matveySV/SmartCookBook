package com.matviienko.smart_cook_book.controller;

import com.matviienko.smart_cook_book.dto.UserDto;
import com.matviienko.smart_cook_book.dto.mapper.UserMapper;
import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import com.matviienko.smart_cook_book.service.UserService;
import com.matviienko.smart_cook_book.exception_handling.UserErrorResponse;
import com.matviienko.smart_cook_book.exception_handling.UserNotCreateException;
import com.matviienko.smart_cook_book.exception_handling.UserNotFoundException;
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
//@RestController
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private final UserService userService;

    @PostMapping("/register")
    public String createUserFromForm(@ModelAttribute("userDto") @Valid UserDto userDto,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "account/register";
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);
        UserDto responseDto = UserMapper.INSTANCE.toDto(
                userService.createUser(userEntity));
        return "redirect:/login";
//        return "Successfull";
    }

    @PostMapping("/register/add")
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


    @GetMapping("/register")
    public String register(Model model) {
        return "registration";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("userDto") UserDto userDto) {
//        if userService.getUsers(userDto) {
//            return "redirect:home";
//        }
//        return "Error response.code = 401";
//    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity< >(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreateException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity< >(response, HttpStatus.BAD_REQUEST);
    }
}