package com.example.expense_tracker.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {


    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
