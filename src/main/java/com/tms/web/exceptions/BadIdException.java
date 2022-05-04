package com.tms.web.exceptions;

public class BadIdException extends RuntimeException{
    public BadIdException(String message) {
        super(message);
    }
}
