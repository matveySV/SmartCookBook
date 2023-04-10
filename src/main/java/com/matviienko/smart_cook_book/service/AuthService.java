package com.matviienko.smart_cook_book.service;

import com.matviienko.smart_cook_book.dto.JWTAuthResponse;
import com.matviienko.smart_cook_book.dto.LoginDto;
import com.matviienko.smart_cook_book.dto.RegisterDto;
import com.matviienko.smart_cook_book.exception_handling.ApiException;
import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import com.matviienko.smart_cook_book.repository.UserRepository;
import com.matviienko.smart_cook_book.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDto.getUsername());
        userEntity.setEmail(registerDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));

//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName("ROLE_USER").get();
//        roles.add(userRole);
//        user.setRoles(roles);

        userRepository.save(userEntity);

        return "User registered successfully!.";
    }
}
