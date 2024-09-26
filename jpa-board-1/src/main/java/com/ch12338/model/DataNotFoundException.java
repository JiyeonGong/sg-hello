package com.ch12338.model;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(){}
    public DataNotFoundException(String message){
        super(message);
    }
}
