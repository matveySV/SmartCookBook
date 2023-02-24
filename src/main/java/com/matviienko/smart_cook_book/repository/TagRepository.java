package com.matviienko.smart_cook_book.repository;

import com.matviienko.smart_cook_book.repository.Entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {

}
