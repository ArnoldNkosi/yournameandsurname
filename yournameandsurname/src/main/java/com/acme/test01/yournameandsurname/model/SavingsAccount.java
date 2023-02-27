package com.acme.test01.yournameandsurname.model;
import lombok.Data;

@Data

public class SavingsAccount  extends AccountModel {
    public SavingsAccount(int customerNum,long accountId, double balance) {
        super(customerNum,accountId, balance);
    }
}
