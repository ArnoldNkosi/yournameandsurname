package com.acme.test01.yournameandsurname.service;
import com.acme.test01.yournameandsurname.config.AccountType;
import com.acme.test01.yournameandsurname.exception.AccountNotFoundException;
import com.acme.test01.yournameandsurname.exception.WithdrawalAmountTooLargeException;
import com.acme.test01.yournameandsurname.model.SavingsAccount;
import com.acme.test01.yournameandsurname.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountImplementationService implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountBusinessRules accountBusinessRules;
    private final PopulateAccount populateAccount;
    public AccountImplementationService(PopulateAccount populateAccount) {
        this.populateAccount = populateAccount;
    }
    @PostConstruct
    public void setUp() {
        populateAccount.listOfAccounts();
    }
    @Override
    public void openSavingsAccount(Long accountId, double amountToDeposit) {
        //if business rules approve then create the account.
        if (accountBusinessRules.checkInitialDeposit(accountId, amountToDeposit, "savings_account")) {
            //this would then call the saveAndFlush method in order to save the object in the database, in this case I'll just add it to the list of accounts.
            accountRepository.createSavingsAccount(accountId, amountToDeposit);
        }
    }
    @Override
    public void openCurrentAccount(Long accountId) {
        //if business rules approve then create the account.
        if (accountBusinessRules.checkIfAccountExists(accountId) != null) {
            accountRepository.createChequeAccount(accountId);
        }
    }
    @Override
    public void withdraw(Long accountId, double amountToWithdraw) throws WithdrawalAmountTooLargeException {
        AccountType accountType = accountBusinessRules.CheckWithdrawal(accountId, amountToWithdraw);
        if(accountType!=null)  accountRepository.deductAccount(accountId, amountToWithdraw, accountType.getDescription());
    }
    @Override
    public void deposit(Long accountId, double amountToDeposit) throws AccountNotFoundException {
        AccountType type = accountBusinessRules.CheckDepositAndAccountType(accountId);
        accountRepository.depositAccount(accountId, amountToDeposit,type.getDescription());
    }

    public List<SavingsAccount> fetchSavingsAccounts() {
     return   populateAccount.listOfSavings();
    }
}
