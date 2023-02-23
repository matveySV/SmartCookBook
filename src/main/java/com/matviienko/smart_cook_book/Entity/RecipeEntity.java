package com.matviienko.smart_cook_book.Entity;

import com.matviienko.smart_cook_book.Entity.ENUM.ComplexityLevel;
import com.matviienko.smart_cook_book.Entity.ENUM.YieldTypeChoice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipe_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String note;

    @Column(nullable = false)
    private boolean isfavorite;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private String instruction;

    private Integer rate;

    @Column(nullable = false)
    private int yield;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private YieldTypeChoice yield_type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplexityLevel complexity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity userEntity;

}
