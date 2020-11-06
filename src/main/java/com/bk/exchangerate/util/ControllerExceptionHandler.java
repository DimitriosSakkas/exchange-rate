package com.bk.exchangerate.util;

import com.bk.exchangerate.exception.WrongDateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = WrongDateException.class)
    public ResponseEntity<ErrorMessage> wrongDateException(WrongDateException ex, WebRequest request) {
        //log.error("Invalid Input Exception: ",ex.getMessage());
        //return new ResponseEntity<ErrorMessage>(new ErrorMessage()ex.getMessage(), HttpStatus.BAD_REQUEST);
        return null;
    }

}
