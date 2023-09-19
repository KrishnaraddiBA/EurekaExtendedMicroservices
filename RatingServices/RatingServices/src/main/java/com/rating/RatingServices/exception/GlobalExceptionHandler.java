package com.rating.RatingServices.exception;

import com.rating.RatingServices.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorDetails> habdleGlobalException(Exception exception, WebRequest webRequest){
    ErrorDetails er=new ErrorDetails(new Date(),exception.getMessage(),webRequest.getDescription(false));
return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

}
}
