package com.example.LoginProject.exception;

public class DatabaseErrorException extends RuntimeException{
    public DatabaseErrorException(){
        super("Database error");
    }
}
