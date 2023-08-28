package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidDataException(MethodArgumentNotValidException ex){

        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage()) // Get the default message for each field error
                .collect(Collectors.toList());

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),errorMessages.get(0));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex){

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST) ;
    }




}
