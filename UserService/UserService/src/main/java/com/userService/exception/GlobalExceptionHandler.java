package com.userService.exception;

import com.userService.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResurceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResurceNotFoundException exception, WebRequest webRequest) {

        ErrorDetails er = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest webRequest) {
        ErrorDetails e = new ErrorDetails(new Date(), ex.getMessage(),webRequest.getDescription(false));
    return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
    }
}
