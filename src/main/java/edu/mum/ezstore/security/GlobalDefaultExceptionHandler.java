package edu.mum.ezstore.security;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.egen.exhandle.exception.AuthorizationException;
import com.egen.exhandle.exception.UnknownResourceException;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ExceptionHandler(value=NoHandlerFoundException.class)
    public String pageNotFound(NoHandlerFoundException e,HttpServletRequest req) {
    	throw new UnknownResourceException();
    }
    
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value=BadCredentialsException.class)
    public String badCredential(NoHandlerFoundException e,HttpServletRequest req) {
    	return "UNAUTHORIZED";
    }

    //handle Access Denied when using @PreAuthorized
    @ResponseStatus(value=HttpStatus.FORBIDDEN)
    @ExceptionHandler(value=AccessDeniedException.class)
    public String accessDenied(HttpServletRequest req, AccessDeniedException e,Principal principal) {
    	throw new AuthorizationException();
    }

}