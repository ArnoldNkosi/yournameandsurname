package com.acme.test01.yournameandsurname.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
    }
    public AccountNotFoundException(String message) {
        super(message);
    }

}