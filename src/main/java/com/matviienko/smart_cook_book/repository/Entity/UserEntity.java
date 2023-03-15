package com.matviienko.smart_cook_book.repository.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
//    @JsonManagedReference
//    @OneToMany(mappedBy = "userEntity",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private List<RecipeEntity> recipeEntityList;

    @OneToMany(mappedBy = "userId")
    private List<RecipeEntity> recipes = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<TagEntity> tags = new ArrayList<>();

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
