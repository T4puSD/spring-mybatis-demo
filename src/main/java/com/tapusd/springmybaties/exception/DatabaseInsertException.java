package com.tapusd.springmybaties.exception;

public class DatabaseInsertException extends RuntimeException {

    public DatabaseInsertException(String message) {
        super(message);
    }

    public DatabaseInsertException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
