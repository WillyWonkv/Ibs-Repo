package com.example.LoginProject.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("User not found");
    }
}
