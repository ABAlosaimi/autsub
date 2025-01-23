package com.autsub.autsub.Exception;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(){
        super("sorry you have to login or Sign up first to use this service");
    }
}
