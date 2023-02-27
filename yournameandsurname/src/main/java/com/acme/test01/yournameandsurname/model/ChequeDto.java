package com.acme.test01.yournameandsurname.model;

import java.io.Serializable;

public class ChequeDto implements Serializable {
    private long accountId;
    private int customerNum;
    private double balance;
    private double amountToDeposit;
    private double amountToWithdraw;
    private double negativeBalance;
    private double overdraft;

    public ChequeDto(long accountId, int customerNum, double balance, double amountToDeposit, double amountToWithdraw, double negativeBalance, double overdraft) {
        this.accountId = accountId;
        this.customerNum = customerNum;
        this.balance = balance;
        this.amountToDeposit = amountToDeposit;
        this.amountToWithdraw = amountToWithdraw;
        this.negativeBalance = negativeBalance;
        this.overdraft = overdraft;
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

    public double getNegativeBalance() {
        return negativeBalance;
    }

    public void setNegativeBalance(double negativeBalance) {
        this.negativeBalance = negativeBalance;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public String toString() {
        return "ChequeDto{" +
                "accountId=" + accountId +
                ", customerNum=" + customerNum +
                ", balance=" + balance +
                ", amountToDeposit=" + amountToDeposit +
                ", amountToWithdraw=" + amountToWithdraw +
                ", negativeBalance=" + negativeBalance +
                ", overdraft=" + overdraft +
                '}';
    }
}
