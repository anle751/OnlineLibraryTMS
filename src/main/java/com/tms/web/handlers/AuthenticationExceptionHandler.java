package com.tms.web.handlers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@ControllerAdvice
//@Component
public class AuthenticationExceptionHandler implements AccessDeniedHandler {

//    @ExceptionHandler({BadCredentialsException.class})
//    public void process1(BadCredentialsException e){
//        System.out.println("fail"+e.getMessage());
//    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("fail"+accessDeniedException.getMessage());
    }
}
