package com.acme.test01.yournameandsurname.service;

import com.acme.test01.yournameandsurname.exception.AccountNotFoundException;
import com.acme.test01.yournameandsurname.exception.WithdrawalAmountTooLargeException;

public interface AccountService {

    void openSavingsAccount(Long accountId, double amountToDeposit);
    void openCurrentAccount(Long accountId);
    void withdraw(Long accountId, double amountToWithdraw)
            throws AccountNotFoundException, WithdrawalAmountTooLargeException, WithdrawalAmountTooLargeException;
    void deposit(Long accountId, double amountToDeposit)throws
            AccountNotFoundException;
}
