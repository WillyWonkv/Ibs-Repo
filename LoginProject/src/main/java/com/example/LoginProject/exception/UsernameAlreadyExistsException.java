package com.example.LoginProject.exception;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String message){
        super(message);
    }

}
