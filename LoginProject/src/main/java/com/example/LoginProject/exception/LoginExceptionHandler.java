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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class LoginExceptionHandler extends ResponseEntityExceptionHandler {

    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

    private Map<String, Object> bodyBuilder(String error, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("error", error);
        body.put("message", message);
        body.put("timestamp", timestamp);
        return body;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralExceptions(Exception e){

        log.error(e.getMessage());

        return ResponseEntity.internalServerError()
                .body(bodyBuilder("INTERNAL SERVER ERROR",e.getMessage()));
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex){

        log.error(ex.getMessage());

        return ResponseEntity.badRequest()
                .body(bodyBuilder("USERNAME ALREADY EXISTS",ex.getMessage()));

    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFound(RoleNotFoundException ex){

        log.error(ex.getMessage());

        return ResponseEntity.badRequest()
                .body(bodyBuilder("ROLE NOT FOUND",ex.getMessage()));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request){

        log.error(ex.getMessage());

        return ResponseEntity.badRequest()
                .body(bodyBuilder("VALIDATION FAILED", "Email OR Password DOES NOT MATCH"));
    }

}
