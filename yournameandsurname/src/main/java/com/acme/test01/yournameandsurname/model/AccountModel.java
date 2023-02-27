package com.acme.test01.yournameandsurname.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountModel {

    private long accountId;
    private int customerNum;
    private double balance;
    private double amountToDeposit;
    private double amountToWithdraw;

    public AccountModel(long accountId) {
        this.accountId = accountId;
    }

    public AccountModel(int customerNum, long accountId, double balance) {
    }

    public AccountModel(int customerNum, double balance) {
    }
}
