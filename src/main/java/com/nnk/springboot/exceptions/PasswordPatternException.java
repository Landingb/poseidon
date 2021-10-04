package com.nnk.springboot.exceptions;

public class PasswordPatternException extends Exception {

    public PasswordPatternException() {
        super();
    }

    public PasswordPatternException(String s) {
        super(s);
    }
}
