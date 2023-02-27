package com.acme.test01.yournameandsurname;

import com.acme.test01.yournameandsurname.config.AccountType;
import com.acme.test01.yournameandsurname.exception.WithdrawalAmountTooLargeException;
import com.acme.test01.yournameandsurname.model.ChequeAccount;
import com.acme.test01.yournameandsurname.model.SavingsAccount;
import com.acme.test01.yournameandsurname.service.AccountBusinessRules;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class YournameandsurnameApplicationTests {

	@Test
	void contextLoads() {
	}
	@InjectMocks
	private AccountBusinessRules service;
	@Mock
	SavingsAccount savingsAccount;
	@Mock
	ChequeAccount chequeAccount;
	@Before
	public void prepare() {

		MockitoAnnotations.initMocks(this);

		List<SavingsAccount> listOfSavingsAccounts = new ArrayList<>();
		listOfSavingsAccounts.add(new SavingsAccount(1, 0, 2000));
		listOfSavingsAccounts.add(new SavingsAccount(2, 0, 5000));
		listOfSavingsAccounts.add(savingsAccount);

		List<ChequeAccount> listOfChequeAccounts = new ArrayList<>();
		listOfChequeAccounts.add(new ChequeAccount(3, 3,1000));
		listOfChequeAccounts.add(new ChequeAccount(4,4, 5000));
		listOfChequeAccounts.add(chequeAccount);


	}
	@Test
	void checkIfAccountExistsTest() {
		long accountId = savingsAccount.getAccountId();
		when(service.checkIfAccountExists(accountId)).thenReturn(false);
		assertEquals(false, service.checkIfAccountExists(accountId));
	}
	@Test
	void checkInitialDepositTest() {
		long accountId = savingsAccount.getAccountId();
		double amount = 1000;
		when(service.checkInitialDeposit(accountId,amount,"savings_account"));
	}
	@Test
	void SavingsWithdrawalTest() throws WithdrawalAmountTooLargeException {

		long accountId = savingsAccount.getAccountId();
		double amount = 1000;
		long customerNum = savingsAccount.getCustomerNum();
		try {
			when(service.SavingsWithdrawal(amount,savingsAccount));
		} catch (Exception ex) {
			throw new WithdrawalAmountTooLargeException(ex.getMessage());
		}
	}
	@Test
	void ChequewithdrawalTest() throws WithdrawalAmountTooLargeException {
		long accountId = chequeAccount.getAccountId();
		double amount = 30000;
		try {
			when(service.Chequewithdrawal(amount,chequeAccount));
		} catch (Exception ex) {
			throw new WithdrawalAmountTooLargeException(ex.getMessage());
		}
	}
	@Test
	void CheckDepositAndAccountTypeTest() {

		long accountId = savingsAccount.getAccountId();
		when(service.CheckDepositAndAccountType(accountId)).thenReturn(AccountType.CHEQUE_ACCOUNT);
		//assertEquals(AccountType.CHEQUE_ACCOUNT, service.checkIfAccountExists(accountId));
	}
}
