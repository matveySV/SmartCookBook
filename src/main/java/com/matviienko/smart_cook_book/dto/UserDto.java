package com.matviienko.smart_cook_book.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
public class UserDto {
    private Integer userId;
    @NotEmpty(message = "Username should not be empty")
    @Size(min=2, max=50, message = "Username should be between 2 and 50 characters")
    private String username;
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @Size(min = 3, max = 20, message = "Password size must 3-20 symbols")
    private String password;
//@Min(value = 0, message = "Age should be greater than 0") - for numbers!!!
}
