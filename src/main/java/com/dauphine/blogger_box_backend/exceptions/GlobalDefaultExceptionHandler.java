package com.dauphine.blogger_box_backend.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // [cite: 561]
public class GlobalDefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler({ // [cite: 564]
        CategoryNotFoundByIdException.class,
        PostNotFoundByIdException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        logger.warn("[NOT FOUND] {}", ex.getMessage()); // [cite: 569]
        return ResponseEntity
                .status(404) // [cite: 571]
                .body(ex.getMessage()); // [cite: 572]
    }
}