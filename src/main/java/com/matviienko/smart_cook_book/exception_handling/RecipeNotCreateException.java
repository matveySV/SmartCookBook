package com.matviienko.smart_cook_book.exception_handling;

public class RecipeNotCreateException extends RuntimeException{
    public RecipeNotCreateException(String msg){
        super(msg);
    }
}
