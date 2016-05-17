package edu.mum.ezstore.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.egen.exhandle.exception.AuthenticationException;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value=AuthenticationException.class)
    public String handleConflict(NoHandlerFoundException e,HttpServletRequest req) {
    	return "UNAUTHORIZED";
    }

}