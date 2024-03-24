package com.springboot.architectural.exception;

public class FileIsExistException extends Exception{
    public  FileIsExistException(String mess) {
        super(mess);
    }
}
