package com.acme.test01.yournameandsurname.service;

import com.acme.test01.yournameandsurname.config.AccountType;
import com.acme.test01.yournameandsurname.exception.AccountNotFoundException;
import com.acme.test01.yournameandsurname.exception.WithdrawalAmountTooLargeException;
import com.acme.test01.yournameandsurname.model.ChequeAccount;
import com.acme.test01.yournameandsurname.model.SavingsAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountBusinessRules {
    private final PopulateAccount populateAccount;
    public AccountBusinessRules(PopulateAccount populateAccount) {
        this.populateAccount = populateAccount;
    }

    // All the rules will be applied here.
    public <T> Object checkIfAccountExists(Long accountId) throws AccountNotFoundException {

        //this method just checks if there's an account with the same accountid
        //if an account with the same account id already exists then return the object.

        SavingsAccount savingsAccount = populateAccount.listOfSavings().stream().filter(account -> accountId.equals(account.getAccountId())).findFirst().orElse(null);
        if (savingsAccount != null) {
            return savingsAccount;
        }
        ChequeAccount chequeAccount = populateAccount.listOfChequeAccounts().stream().filter(account -> accountId.equals(account.getAccountId())).findFirst().orElse(null);
        if (chequeAccount != null) {
            return chequeAccount;
        } else return null;
    }
    public Boolean checkInitialDeposit(Long accountId, double initialDeposit, String accountType) {
        //this method is called upon open account.
        // a) it will check if there's an account for this accountId
        // b) if there isn't then it will check if the inital deposit amount is equals to or more than R2000 if account type is savings
        // if these two pass then the account will be created.
        boolean validRequest = false;
        if (checkIfAccountExists(accountId) != null) {
            switch (accountType) {
                case "cheque_account":
                    validRequest = true;
                    break;
                case "savings_account":
                    if (initialDeposit >= 2000.0) validRequest = true;
                    break;
                default:
                    validRequest = false;
            }
        }
        return validRequest;
    }
    public Boolean SavingsWithdrawal(double amountToWithdraw, SavingsAccount account) throws WithdrawalAmountTooLargeException {
        //   this method is called upon withdrawal.
        //All it does is check the account type and balance
        // if it's a savings account and the balance is R2000 then the request will be denied(return false).
        //else check if the balance after the withdrawal is less than R2000,
        // if it is then deny the request again and send a message to say how much can be withdrawn.
        double minBalance = 2000.00;
     try {
                double balance = account.getBalance() - amountToWithdraw;
                if (balance >= minBalance) {
                    return true;
                }
            } catch (Exception ex) {
                throw new WithdrawalAmountTooLargeException(ex.getMessage());
            }
        return false;
    }
    public Boolean Chequewithdrawal(double amountToWithdraw, ChequeAccount account) throws WithdrawalAmountTooLargeException {

        //1. Check account balance.
        //   If it is already -100k then return false;
        //2. Check how much the balance is after withdrawal.
        //   If balance  after withdrawal is less or equals to -100k then approve else deny.
        double minBalance = -100000.00;
        try {
            double balance = account.getBalance() - amountToWithdraw;
            if (balance >= minBalance) {
                return true;
            }
        } catch (Exception ex) {
            throw new WithdrawalAmountTooLargeException(ex.getMessage());
        }
        return false;
    }
    public AccountType CheckWithdrawal(Long accountId, double amountToWithdraw) throws WithdrawalAmountTooLargeException {
        Object account = checkIfAccountExists(accountId);
        if (account == null) {
            return null;
        } else {
            String type = account.getClass().getTypeName();
            if ((type != null) && (type.contains("ChequeAccount"))) {
                ChequeAccount chequeAccount = (ChequeAccount) account;
                if (Chequewithdrawal(amountToWithdraw, chequeAccount))
                    return AccountType.CHEQUE_ACCOUNT;
            } else if ((type != null) && (type.contains("SavingsAccount"))) {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                if (SavingsWithdrawal(amountToWithdraw, savingsAccount)) return AccountType.SAVINGS_Account;
            }
        }
        return null;
    }
    public AccountType CheckDepositAndAccountType(Long accountId) throws AccountNotFoundException {
        Object account = checkIfAccountExists(accountId);
        if (account == null) {
            return null;
        } else {
            String type = account.getClass().getTypeName();

            if ((type != null) && (type.contains("SavingsAccount"))) {
                return AccountType.SAVINGS_Account;
            } else if ((type != null) && (type.contains("ChequeAccount"))) {
                return AccountType.CHEQUE_ACCOUNT;
            }
        }
        return null;
    }
}
