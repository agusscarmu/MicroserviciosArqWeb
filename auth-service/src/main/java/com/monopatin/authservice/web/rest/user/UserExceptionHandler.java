package com.monopatin.authservice.web.rest.user;

import com.monopatin.authservice.service.exception.user.ErrorDTO;
import com.monopatin.authservice.service.exception.user.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice( basePackages = "com.monopatin.authservice.web.rest.USER" )
public class UserExceptionHandler {

    @ExceptionHandler( UserException.class )
    public ErrorDTO getUserException( UserException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}
