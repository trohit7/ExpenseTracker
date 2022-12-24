package com.example.expense_tracker.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
