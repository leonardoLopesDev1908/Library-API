package com.jpa.libraryapi.handlers;

import com.jpa.libraryapi.common.ApiResponse;
import com.jpa.libraryapi.exceptions.DuplicatedRegisterException;
import com.jpa.libraryapi.exceptions.InvalidFieldException;
import com.jpa.libraryapi.exceptions.NotAllowedOperationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedRegisterException.class)
    public ApiResponse<String> handleDuplicatedRegister(DuplicatedRegisterException e) {
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ApiResponse<String> handleInvalidField(InvalidFieldException e) {
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(NotAllowedOperationException.class)
    public ApiResponse<String> handleNotAllowedOperation(NotAllowedOperationException e) {
        return ApiResponse.error(e.getMessage());
    }
}
