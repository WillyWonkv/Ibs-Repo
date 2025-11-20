package com.example.LoginProject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class UsersExceptionHandler extends ResponseEntityExceptionHandler {

    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    private Map<String, Object> bodyBuilder(String error, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("error", error);
        body.put("message", message);
        body.put("timestamp", timestamp);
        return body;
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<Object> handleUserNotFound(EmptyListException ex) {

        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(bodyBuilder("EMPTY LIST", ex.getMessage()));

    }


}
