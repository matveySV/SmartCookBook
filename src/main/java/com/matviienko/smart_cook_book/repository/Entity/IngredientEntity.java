package com.matviienko.smart_cook_book.repository.Entity;

import com.matviienko.smart_cook_book.repository.Entity.ENUM.UnitChoice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ingredient")
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "amount")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private UnitChoice unit;

    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeEntity> recipes;

}



//@Column(name = "amount")
//    private Integer amount;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "unit")
//    private UnitChoice unitChoice;