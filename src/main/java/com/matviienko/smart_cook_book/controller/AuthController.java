package com.matviienko.smart_cook_book.controller;

import com.matviienko.smart_cook_book.controller.rest.AuthRestController;
import com.matviienko.smart_cook_book.dto.UserDto;
import com.matviienko.smart_cook_book.dto.mapper.UserMapper;
import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import com.matviienko.smart_cook_book.security.UsersDetails;
import com.matviienko.smart_cook_book.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userDto") UserDto userDto) {
        return "auth/registration";

    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }


    @GetMapping("/show-user-info")
    @ResponseBody
    public int showUserInfo(@AuthenticationPrincipal UsersDetails usersDetails) {
        System.out.println(usersDetails.getUserEntity().getUserId());
        return usersDetails.getUserEntity().getUserId();
    }

    @PostMapping("/registration")
    public String formRegistration(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(userDto);
            return "auth/registration";
        }
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);
        UserDto responseDto = UserMapper.INSTANCE.toDto(
                userService.createUser(userEntity));
        return "redirect:/auth/login";

    }
}
