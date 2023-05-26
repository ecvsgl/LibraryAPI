package com.ecvsgl.libraryproject.exception;

public class AlreadyExistingBookEntityException extends RuntimeException{
    public AlreadyExistingBookEntityException (String msg) {
        super(msg);
    }
}
