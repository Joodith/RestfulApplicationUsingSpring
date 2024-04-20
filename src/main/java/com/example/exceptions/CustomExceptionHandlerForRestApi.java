package com.example.exceptions;

import com.example.modelResponse.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandlerForRestApi extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex,WebRequest webRequest){
        String errorMessageDescription=ex.getLocalizedMessage();
        if(errorMessageDescription==null) errorMessageDescription=ex.toString();
        ErrorMessage errorMessage=new ErrorMessage(new Date(),errorMessageDescription);
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest webRequest){
        String errorMessageDescription=ex.getLocalizedMessage();
        if(errorMessageDescription==null) errorMessageDescription=ex.toString();
        ErrorMessage errorMessage=new ErrorMessage(new Date(),errorMessageDescription);
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={UserServiceException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorMessage handleUserServiceException(UserServiceException ex, WebRequest webRequest){
        String errorMessageDescription=ex.getLocalizedMessage();
        if(errorMessageDescription==null) errorMessageDescription=ex.toString();
        return new ErrorMessage(new Date(),errorMessageDescription);
    }
}
