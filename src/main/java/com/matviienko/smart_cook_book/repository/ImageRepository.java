package com.matviienko.smart_cook_book.repository;

import com.matviienko.smart_cook_book.repository.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
}
