package com.acme.test01.yournameandsurname.model;

import java.io.Serializable;

public class SavingsDto implements Serializable {

    private long accountId;
    private int customerNum;
    private double balance;
    private double amountToDeposit;
    private double amountToWithdraw;

    public SavingsDto(long accountId, int customerNum, double balance, double amountToDeposit, double amountToWithdraw) {
        this.accountId = accountId;
        this.customerNum = customerNum;
        this.balance = balance;
        this.amountToDeposit = amountToDeposit;
        this.amountToWithdraw = amountToWithdraw;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmountToDeposit() {
        return amountToDeposit;
    }

    public void setAmountToDeposit(double amountToDeposit) {
        this.amountToDeposit = amountToDeposit;
    }

    public double getAmountToWithdraw() {
        return amountToWithdraw;
    }

    public void setAmountToWithdraw(double amountToWithdraw) {
        this.amountToWithdraw = amountToWithdraw;
    }

    @Override
    public String toString() {
        return "SavingsDto{" +
                "accountId=" + accountId +
                ", customerNum=" + customerNum +
                ", balance=" + balance +
                ", amountToDeposit=" + amountToDeposit +
                ", amountToWithdraw=" + amountToWithdraw +
                '}';
    }
}

