package org.example.first_pr.adapters.api.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleAppErrors(AppError ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleUnknownException(Exception ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity
                .status(500)
                .body(new ErrorMessage(500, "Unexpected error"));
    }
}
