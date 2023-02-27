package com.acme.test01.yournameandsurname.model;

import lombok.Data;

@Data
public class ChequeAccount extends AccountModel{
    private double negativeBalance;
    private double overdraft;

    public ChequeAccount(){}

    public ChequeAccount(int customerNum,long accountId, double balance) {
        super(customerNum, balance);
    }
}
