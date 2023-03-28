package com.matviienko.smart_cook_book.service;

import com.matviienko.smart_cook_book.exception_handling.DataNotFoundException;
import com.matviienko.smart_cook_book.repository.Entity.RecipeEntity;
import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import com.matviienko.smart_cook_book.repository.UserRepository;
import com.matviienko.smart_cook_book.security.UsersDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Value("${constant.expired}")
//    private int expired;


    public void createUser(String username, String email, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        System.out.println(userEntity);
        userRepository.save(userEntity);
    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findOne(int id) {
        Optional<UserEntity> foundClient = userRepository.findById(id);
        return foundClient.orElse(null);
    }

    public UserEntity createUser(UserEntity userEntity) {
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        return userRepository.save(userEntity);
    }

    public int getCurrentUserId() {
        return ((UsersDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEntity().getUserId();
    }

    public void deleteUser(Integer userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(DataNotFoundException::new);
        userRepository.delete(userEntity);
    }
}