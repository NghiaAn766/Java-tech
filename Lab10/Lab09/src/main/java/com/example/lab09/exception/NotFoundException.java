package com.example.lab09.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super((message));
    }
}
