package com.sif.claims.exceptions;

public class CustomClaimException extends RuntimeException {
    public CustomClaimException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
