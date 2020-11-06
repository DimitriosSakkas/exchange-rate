package com.bk.exchangerate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongDateException extends RuntimeException {

    public WrongDateException(String message) {
        super(message);
    }
}
