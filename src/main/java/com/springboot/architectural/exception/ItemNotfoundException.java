package com.springboot.architectural.exception;

public class ItemNotfoundException extends Exception{
    public ItemNotfoundException(String mess){
        super(mess);
    }
}
