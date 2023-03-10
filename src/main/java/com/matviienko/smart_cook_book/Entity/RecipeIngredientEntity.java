package com.matviienko.smart_cook_book.Entity;

import com.matviienko.smart_cook_book.Entity.ENUM.UnitChoice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id")
    private Integer recipeIngredientId;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private RecipeEntity recipeEntity;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")
    private IngredientEntity ingredientEntity;

    @Column(name = "amount")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private UnitChoice unitChoice;

}