package com.webapp.bankingportal.service;

import com.webapp.bankingportal.entity.Account;
import com.webapp.bankingportal.entity.Users;

public interface AccountService {

	public Account createAccount(Users users);
	public void cashDeposit(String accountNumber, double amount);
	public void cashWithdrawal(String accountNumber, double amount);
	
	
}
