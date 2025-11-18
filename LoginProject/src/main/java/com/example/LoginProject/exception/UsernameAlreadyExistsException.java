package com.example.LoginProject.exception;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(){
        super("Username already exists");
    }

}
