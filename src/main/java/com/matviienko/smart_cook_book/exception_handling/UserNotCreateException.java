package com.matviienko.smart_cook_book.exception_handling;

public class UserNotCreateException extends RuntimeException{
    public UserNotCreateException(String msg){
        super(msg);
    }
}
