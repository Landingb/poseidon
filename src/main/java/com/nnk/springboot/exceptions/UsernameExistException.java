package com.nnk.springboot.exceptions;

public class UsernameExistException extends Exception {

    public UsernameExistException() {
        super();
    }

    public UsernameExistException(String s) {
        super(s);
    }
}
