package com.sm.im;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, NoItemsFoundException.class,
            ItemNotFoundException.class, InvalidItemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
