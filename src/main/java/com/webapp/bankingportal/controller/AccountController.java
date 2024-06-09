package com.webapp.bankingportal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webapp.bankingportal.dto.*;
import com.webapp.bankingportal.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bankingportal.service.AccountService;
import com.webapp.bankingportal.service.TransactionService;
import com.webapp.bankingportal.util.LoggedinUser;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private DashboardService dashboardService;

	@PostMapping("/deposit")
	public ResponseEntity<?> cashDeposit(@RequestBody AmountRequest amountRequest) {

		if (amountRequest.getAmount() <= 0) {
			Map<String, String> err = new HashMap<>();
			err.put("Error", "Invalid amount");
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		}

		accountService.cashDeposit(LoggedinUser.getAccountNumber(), amountRequest.getAmount());

		Map<String, String> response = new HashMap<>();
		response.put("msg", "Cash deposited successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> cashWithdrawal(@RequestBody AmountRequest amountRequest) {
		if (amountRequest.getAmount() <= 0) {
			Map<String, String> err = new HashMap<>();
			err.put("Error", "Invalid amount");
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		}
		accountService.cashWithdrawal(LoggedinUser.getAccountNumber(), amountRequest.getAmount());

		Map<String, String> response = new HashMap<>();
		response.put("msg", "Cash withdrawn successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionDTO>> getAllTransactionsByAccountNumber() {
		List<TransactionDTO> transactions = transactionService
				.getAllTransactionsByAccountNumber(LoggedinUser.getAccountNumber());
		return ResponseEntity.ok(transactions);
	}

	@GetMapping("/accountdetails")
	public ResponseEntity<AccountResponse> getAccountDetails() {
		String accountNumber = LoggedinUser.getAccountNumber();
		AccountResponse accountResponse = dashboardService.getAccountDetails(accountNumber);
		return ResponseEntity.ok(accountResponse);
	}
}
