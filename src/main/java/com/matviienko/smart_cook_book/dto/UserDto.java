package com.matviienko.smart_cook_book.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private Integer userId;
    @NotEmpty
    @Size(min = 2, max = 50, message = "Username should be between 2 and 50 characters")
    private String username;
    @Email
    @NotEmpty
    private String email;
    @Size(min = 3, max = 20, message = "Password size must 3-20 symbols")
    private String password;
}
