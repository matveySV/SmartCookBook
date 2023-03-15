package com.matviienko.smart_cook_book.exception_handling;

import lombok.Getter;

public class RecipeNotFoundException extends RuntimeException {
  //  private static final String MESSAGE = "Recipe doesn't exist";

    public RecipeNotFoundException() {
        super("ERROR! Recipe doesn't exist");

  //  public RecipeNotFoundException() {

    }
}
//    @Getter
//    private static final String MESSAGE = "Recipe doesn't exist";
//}
