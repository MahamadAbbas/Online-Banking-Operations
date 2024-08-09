package com.examplebank.OBO.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value; 
import com.examplebank.OBO.entities.BankingAccount;
import com.examplebank.OBO.repositories.BankingAccountRepository;

import java.math.BigDecimal;

@Service
public class BankingService {

	@Autowired
	private BankingAccountRepository accountRepository;


	@Value("${interest.rate:0.05}") // Default interest rate is 5% if not specified
	private BigDecimal interestRate;

	@Transactional
	public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {
		BankingAccount fromAccount = accountRepository.findById(fromAccountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));
		BankingAccount toAccount = accountRepository.findById(toAccountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		if (fromAccount.getBalance().compareTo(amount) < 0) {
			throw new RuntimeException("Insufficient funds");
		}

		fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
		toAccount.setBalance(toAccount.getBalance().add(amount));

		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
	}

	@Transactional
	public void applyInterest() {
		// Retrieve all accounts
		Iterable<BankingAccount> accounts = accountRepository.findAll();

		// Apply interest to each account
		for (BankingAccount account : accounts) {
			BigDecimal currentBalance = account.getBalance();
			BigDecimal interest = currentBalance.multiply(interestRate);
			account.setBalance(currentBalance.add(interest));
		}

		// Save updated accounts
		accountRepository.saveAll(accounts);
	}
}
