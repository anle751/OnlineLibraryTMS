package com.tms.web.handlers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TestHandler {
    @ExceptionHandler({AccessDeniedException.class})
    public void catchException(AccessDeniedException e){
        System.out.println("sss fail_____________:" + e.getMessage());
    }
}
