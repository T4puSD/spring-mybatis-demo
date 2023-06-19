package com.tapusd.springmybaties.config.advice;

import com.tapusd.springmybaties.dto.response.Response;
import com.tapusd.springmybaties.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Response<Object>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Response<>()
                        .setCode(HttpStatus.NOT_FOUND.value())
                        .setMessage(ex.getMessage())
                );
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Response<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Response<>()
                        .setCode(HttpStatus.BAD_REQUEST.value())
                        .setMessage(ex.getMessage())
                );
    }

    // Handling any type of unresolved exception
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Response<Object>> handleAnyException(Exception ex) {
        LOGGER.error("Internal Server Error occurred", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response<>()
                        .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .setMessage(ex.getMessage())
                );
    }
}
