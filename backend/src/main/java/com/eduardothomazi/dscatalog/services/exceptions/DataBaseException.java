package com.eduardothomazi.dscatalog.services.exceptions;

public class DataBaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataBaseException() {
    }

    public DataBaseException(String message) {
        super(message);
    }
}
