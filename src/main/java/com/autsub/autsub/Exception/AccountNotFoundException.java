package com.autsub.autsub.Exception;

public class AccountNotFoundException extends RuntimeException {

     public AccountNotFoundException(){
        super("the Account with the provided Email or Password does not exist pleas try again with correct info");
    }

    public AccountNotFoundException(String meg, Exception exception){
         super(meg,exception);
    }
}
