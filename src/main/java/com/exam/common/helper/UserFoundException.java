package com.exam.common.helper;

public class UserFoundException extends Exception{
    public UserFoundException() {
        super("Username already in used, try another username.");
    }

    public UserFoundException(String msg){
        super(msg);
    }
}
