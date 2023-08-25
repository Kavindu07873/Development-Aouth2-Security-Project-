package com.ceyentra.last.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserException.class})
    ResponseEntity<ErrorMsgResponseDto> handleUserException(UserException userException){
        return new ResponseEntity<>(
                new ErrorMsgResponseDto(false ,userException.getStatus(),userException.getMsg()) ,
                HttpStatus.OK);
    }

    public static final String APPLICATION_ERROR_OCCURRED_MESSAGE = "Application Error Occurred";

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<ErrorMsgResponseDto> handleException(Exception exception){
        return  new ResponseEntity<>(
                new ErrorMsgResponseDto(false,exception.hashCode() ,exception.getMessage()),
          HttpStatus.OK
        );
    }

}
