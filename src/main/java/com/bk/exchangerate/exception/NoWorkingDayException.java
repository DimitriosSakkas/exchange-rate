package com.bk.exchangerate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoWorkingDayException extends RuntimeException {

    public NoWorkingDayException(LocalDate date) {
        super(date.toString());
    }
}
