package com.bk.exchangerate.util;

import com.bk.exchangerate.exception.NoWorkingDayException;
import com.bk.exchangerate.exception.WrongDateException;
import com.bk.exchangerate.model.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {WrongDateException.class})
    public ResponseEntity<ErrorMessage> wrongDateException(WrongDateException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String title = "wrong date exception";
        String description = "date is before 2000 or after yesterday";

        log.error(description + ": {}", ex.getMessage());
        return returnErrorMessage(status, title, description);
    }

    @ExceptionHandler(value = {NoWorkingDayException.class})
    public ResponseEntity<ErrorMessage> noWorkingDayException(NoWorkingDayException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String title = "no working day date exception";
        String description = "day is Saturday or Sunday";

        log.error(description + ": {}", ex.getMessage());
        return returnErrorMessage(status, title, description);
    }

    private ResponseEntity<ErrorMessage> returnErrorMessage(HttpStatus status, String title, String description) {
        ErrorMessage error = new ErrorMessage();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(status.value());
        error.setTitle(title);
        error.setDescription(description);
        return new ResponseEntity<>(error, status);
    }
}
