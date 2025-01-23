package com.autsub.autsub.Exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorResponse errorResponse;

    ApplicationExceptionHandler(ErrorResponse errorResponse){
        this.errorResponse = errorResponse;
    } 

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorsList = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
           errorsList.add(error.getDefaultMessage());
        }

        return new ResponseEntity<>(errorsList,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> AccountNotFoundExceptionHandler(AccountNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
     }


     @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badReqExceptionHandler(BadRequestException ex){
        errorResponse.setMessage(List.of(ex.getMessage()));
        errorResponse.setLocalTime(LocalTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> unauthorizedUserException(UnauthorizedException ex){
     errorResponse.setMessage(List.of(ex.getMessage()));
     errorResponse.setLocalTime(LocalTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
     }

 }
