package com.springboot.architectural.exception;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(String mess) {
        super(mess);
    }
}
