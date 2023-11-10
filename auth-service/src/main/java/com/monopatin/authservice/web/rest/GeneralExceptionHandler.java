package com.monopatin.authservice.web.rest;

import com.monopatin.authservice.service.exception.user.ErrorDTO;
import com.monopatin.authservice.service.exception.user.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice( basePackages = "com.monopatin.authservice.web.rest" )
public class GeneralExceptionHandler {

    @ExceptionHandler( NotFoundException.class )
    public ErrorDTO getException( NotFoundException ex ){
        return new ErrorDTO( ex.getCode(), ex.getMessage() );
    }
}
