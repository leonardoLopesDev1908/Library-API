package com.jpa.libraryapi.exceptions;

public class NotAllowedOperationException extends RuntimeException {
    
    public NotAllowedOperationException(String message){
        super(message);
    }
}
