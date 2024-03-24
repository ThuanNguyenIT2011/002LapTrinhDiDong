package com.springboot.architectural.exception;

public class NotifyNotfoundException extends Exception{
    public NotifyNotfoundException(String mess) {
        super(mess);
    }
}
