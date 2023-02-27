package com.acme.test01.yournameandsurname.repository;

public interface AccountRepository {
    // I would make this class extend the jpa repository so that it can inherit methods such as save , save and flush etc.
    // in this case it will just be used to directly withdraw, deposit into an account that is in the set of objects.
    void createSavingsAccount(Long accountId, double amountToDeposit);
    void createChequeAccount(Long accountId);
    void deductAccount(Long accountId, double amountToWithdraw, String accountType);
    void depositAccount(Long accountId,double amountToWithdraw,String accountType);
}
