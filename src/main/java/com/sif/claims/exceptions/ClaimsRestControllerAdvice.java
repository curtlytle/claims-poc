package com.sif.claims.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClaimsRestControllerAdvice {

    @ExceptionHandler(value = CustomClaimException.class)
    public ResponseEntity<String> handleCustomClaimException(CustomClaimException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> handleContraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>("Claim Number already exists, it must be unique.", HttpStatus.CONFLICT);
    }
}
