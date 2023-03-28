package com.matviienko.smart_cook_book.exception_handling;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg){
        super(msg);
    }

}
