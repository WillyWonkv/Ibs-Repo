package com.example.LoginProject.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class LoginExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(Exception e){

        log.error(e.getMessage());

        Map<String,Object> body = new HashMap<>();
        body.put("error", "INTERNAL SERVER ERROR");
        body.put("message",e.getMessage());
        body.put("timestamp",LocalDateTime.now());

        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex){

        log.error(ex.getMessage());

        Map<String,Object> body = new HashMap<>();
        body.put("error","USERNAME ALREADY EXISTS");
        body.put("message",ex.getMessage());
        body.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(body);

    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFound(RoleNotFoundException ex){

        log.error(ex.getMessage());

        Map<String,Object> body = new HashMap<>();
        body.put("error","ROLE NOT FOUND");
        body.put("message",ex.getMessage());
        body.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(body);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request){

        log.error(ex.getMessage());
        Map<String,Object> body = new HashMap<>();
        body.put("error","VALIDATION FAILED");
        body.put("message","Empty Fields");
        body.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(DatabaseErrorException.class)
    public ResponseEntity<Object> handleDatabaseError(DatabaseErrorException ex){

        log.error(ex.getMessage());

        Map<String,Object> body = new HashMap<>();
        body.put("error","DATABASE ERROR");
        body.put("message",ex.getMessage());
        body.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.badRequest().body(body);

    }


}
