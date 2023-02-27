package com.acme.test01.yournameandsurname.service;


import com.acme.test01.yournameandsurname.model.ChequeAccount;
import com.acme.test01.yournameandsurname.model.SavingsAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PopulateAccount {
    public List<SavingsAccount> listOfSavings(){
        List<SavingsAccount> listOfSavingsAccounts = new ArrayList<>();
        listOfSavingsAccounts.add(new SavingsAccount(1,0,2000));
        listOfSavingsAccounts.add(new SavingsAccount(2,0,5000));
        return listOfSavingsAccounts;
    }

    public List<ChequeAccount> listOfChequeAccounts(){
        List<ChequeAccount> listOfChequeAccounts = new ArrayList<>();
        ChequeAccount account = new ChequeAccount();
        account.setAccountId(3);
        account.setCustomerNum(3);
        account.setBalance(1000);

        listOfChequeAccounts.add(account);
    //    listOfChequeAccounts.add(new ChequeAccount(3,3,1000));
        listOfChequeAccounts.add(new ChequeAccount(4,4,5000));
        return listOfChequeAccounts;
    }

    public <T> List<Object> listOfAccounts(){
        List<Object> listOfBankAccounts = new ArrayList<>();
        listOfBankAccounts.add(listOfSavings());
        listOfBankAccounts.add(listOfChequeAccounts());
        return listOfBankAccounts;
    }
}
