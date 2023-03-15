package com.matviienko.smart_cook_book.repository;

import com.matviienko.smart_cook_book.repository.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
