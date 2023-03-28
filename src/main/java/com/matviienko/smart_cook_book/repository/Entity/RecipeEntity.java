package com.matviienko.smart_cook_book.repository.Entity;

import com.matviienko.smart_cook_book.repository.Entity.ENUM.ComplexityLevel;
import com.matviienko.smart_cook_book.repository.Entity.ENUM.YieldType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipe_id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    private String note;

    @Column
    private boolean isfavorite;

    @Column
    private LocalDate date;

    @Column
    private Integer time;

    @Column
    private String instruction;

    private Integer rate;

    @Column
    private Integer yield;

    @Enumerated(EnumType.STRING)
    @Column(name = "yield_type")
    private YieldType yieldType;

    @Enumerated(EnumType.STRING)
    @Column(name = "complexity_level")
    private ComplexityLevel complexityLevel;

    private Integer userId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<IngredientEntity> ingredients;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "recipe_tag",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags;

}
