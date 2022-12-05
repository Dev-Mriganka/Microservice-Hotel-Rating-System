package com.lone.HotelService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle exception for recourse not found
    @ExceptionHandler(HotelException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(HotelException ex, WebRequest req) {

        ApiResponse apiResponse = ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .success(true)
                .description(req.getDescription(false))
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> notFoundException(Exception ex, WebRequest req) {

        ApiResponse apiResponse = ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .success(true)
                .description(req.getDescription(false))
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse> noHandlerFoundExceptionHandler(NoHandlerFoundException se, WebRequest req) {

        ApiResponse err = new ApiResponse();

        err.setTimestamp(LocalDateTime.now());
        err.setMessage(se.getMessage());
        err.setSuccess(false);
        err.setDescription(req.getDescription(false));

        return new ResponseEntity<ApiResponse>(err, HttpStatus.BAD_REQUEST);

    }
}
