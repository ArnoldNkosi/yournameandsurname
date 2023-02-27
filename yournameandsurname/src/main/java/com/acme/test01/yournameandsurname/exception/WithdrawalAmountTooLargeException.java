package com.acme.test01.yournameandsurname.exception;

public class WithdrawalAmountTooLargeException extends Exception {

    public WithdrawalAmountTooLargeException() {
    }
    public WithdrawalAmountTooLargeException(String message) {
        super(message);
    }

}