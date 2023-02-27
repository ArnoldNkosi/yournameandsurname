package com.acme.test01.yournameandsurname.service;

import com.acme.test01.yournameandsurname.model.ChequeAccount;
import com.acme.test01.yournameandsurname.model.SavingsAccount;
import com.acme.test01.yournameandsurname.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService implements AccountRepository {
    @Autowired
    private PopulateAccount populateAccount;
    @Override
    public void createSavingsAccount(Long accountId, double amountToDeposit) {
        SavingsAccount savingsAccount = new SavingsAccount(populateAccount.listOfAccounts().size()+1,accountId,amountToDeposit);
        populateAccount.listOfSavings().add(savingsAccount);
    }
    @Override
    public void createChequeAccount(Long accountId) {
        ChequeAccount model = new ChequeAccount(populateAccount.listOfAccounts().size()+1,accountId,0);
        populateAccount.listOfChequeAccounts().add(model);
    }
    @Override
    public void deductAccount(Long accountId, double amountToWithdraw, String accountType) {
        try{
                switch (accountType) {
                    case "cheque_account":
                        deductChequeMoney(accountId, amountToWithdraw);
                        break;
                    case "savings_account":
                        deductSavingsMoney(accountId, amountToWithdraw);
                        break;
                }
        }
        catch (Exception ex){
           log.info("Error occurred: " + ex.getMessage());
        }
    }
    @Override
    public void depositAccount(Long accountId, double amountToWithdraw,String accountType) {

        try{
            switch (accountType) {
                case "cheque_account":
                    addChequeMoney(accountId, amountToWithdraw);
                    break;
                case "savings_account":
                    addSavingsMoney(accountId, amountToWithdraw);
                    break;
            }
        }
        catch (Exception ex){
            log.info("Error occurred: " + ex.getMessage());
        }
    }
    public void deductSavingsMoney(Long accountId, double amount){
        SavingsAccount savingsAccount = populateAccount.listOfSavings().stream().filter(account -> accountId.equals(account.getAccountId())).findAny().orElse(null);
        double newBalance = savingsAccount.getBalance()-amount;
        savingsAccount.setBalance(newBalance);
    }
    public void deductChequeMoney(Long accountId, double amount){
        ChequeAccount chequeAccount = populateAccount.listOfChequeAccounts().stream().filter(account -> accountId.equals(account.getAccountId())).findAny().orElse(null);
        double newBalance = chequeAccount.getBalance()-amount;
        chequeAccount.setBalance(newBalance);
    }
    public void addSavingsMoney(Long accountId, double amount){
        SavingsAccount savingsAccount = populateAccount.listOfSavings().stream().filter(account -> accountId.equals(account.getAccountId())).findAny().orElse(null);
        double newBalance = savingsAccount.getBalance()+amount;
        savingsAccount.setBalance(newBalance);
    }
    public void addChequeMoney(Long accountId, double amount){
        ChequeAccount chequeAccount = populateAccount.listOfChequeAccounts().stream().filter(account -> accountId.equals(account.getAccountId())).findAny().orElse(null);
        double newBalance = chequeAccount.getBalance()+amount;
        chequeAccount.setBalance(newBalance);
    }
}
