package com.matviienko.smart_cook_book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthResponse(String token) {
    }
}
