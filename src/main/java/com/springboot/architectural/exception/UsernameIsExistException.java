package com.springboot.architectural.exception;

public class UsernameIsExistException extends Exception{
    public UsernameIsExistException(String mess) {
        super(mess);
    }
}
