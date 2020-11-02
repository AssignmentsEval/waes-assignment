package org.miya.waes.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception handler to catch custom exceptions, then respond http request by ResponseEntity
 *
 * @author Yasin Kızılkaya
 */

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IDNotFoundException.class, SideNotFoundException.class})
    public ResponseEntity<Object> handleNotfoundExceptions(RuntimeException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), NOT_FOUND);
    }
}
