package com.autsub.autsub.Exception;

public class NotActiveAccountException extends RuntimeException{

    public NotActiveAccountException(){
        super("Sorry you have to activate your account first");
    }
    
}
