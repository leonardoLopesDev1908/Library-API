package com.jpa.libraryapi.exceptions;

public class InvalidFieldException extends RuntimeException{
    public InvalidFieldException(String message){ super(message); }
}
