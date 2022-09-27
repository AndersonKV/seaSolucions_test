package com.example.demo.exception;

public class ApiRequestException  extends RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
//    public ApiRequestException(String message, Throwable cause, HttpStatus httpStatus) {
//        super(message, cause, httpStatus);
//    }
}