package org.example.first_pr.adapters.api.exceptions;

import org.example.first_pr.application.base.exceptions.AppError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Map<Class<? extends Throwable>, HttpStatus> EXCEPTION_STATUS_MAP = Map.ofEntries(
            Map.entry(HttpMessageNotReadableException.class, HttpStatus.BAD_REQUEST),
            Map.entry(MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST),
            Map.entry(MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST),
            Map.entry(ConstraintViolationException.class, HttpStatus.BAD_REQUEST),
            Map.entry(MethodArgumentTypeMismatchException.class, HttpStatus.BAD_REQUEST),
            Map.entry(HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED),
            Map.entry(HttpMediaTypeNotSupportedException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE),
            Map.entry(HttpMediaTypeNotAcceptableException.class, HttpStatus.NOT_ACCEPTABLE),
            Map.entry(NoHandlerFoundException.class, HttpStatus.NOT_FOUND)
    );

    @ExceptionHandler(AppError.class)
    public ResponseEntity<ErrorMessage> handleAppErrors(AppError ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAnyException(Exception ex) {
        HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity
                .status(status)
                .body(new ErrorMessage(status.value(), ex.getMessage()));
    }
}
