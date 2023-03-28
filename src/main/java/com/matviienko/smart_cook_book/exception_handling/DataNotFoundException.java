package com.matviienko.smart_cook_book.exception_handling;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("ERROR! Data doesn't exist");

    }
}