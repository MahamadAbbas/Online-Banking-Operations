package com.examplebank.OBO.unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.examplebank.OBO.services.BankingService;
import com.examplebank.OBO.entities.BankingAccount;
import com.examplebank.OBO.repositories.BankingAccountRepository;
import java.math.BigDecimal;

@SpringBootTest
public class BankingServiceTest {

    @Autowired
    private BankingService bankingService;

    @Autowired
    private BankingAccountRepository accountRepository;

    @Test
    public void testTransferMoney() {
        Long fromAccountId = 1L; // Adjust IDs as needed
        Long toAccountId = 2L;
        BigDecimal amount = BigDecimal.valueOf(50);
        
        // Get initial balances
        BankingAccount fromAccountBefore = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        BankingAccount toAccountBefore = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        BigDecimal fromAccountInitialBalance = fromAccountBefore.getBalance();
        BigDecimal toAccountInitialBalance = toAccountBefore.getBalance();

        // Perform the transfer
        bankingService.transferMoney(fromAccountId, toAccountId, amount);

        // Fetch updated account details
        BankingAccount fromAccountAfter = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        BankingAccount toAccountAfter = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        BigDecimal fromAccountFinalBalance = fromAccountAfter.getBalance();
        BigDecimal toAccountFinalBalance = toAccountAfter.getBalance();

        // Assert that balances have been updated correctly
        assertEquals(fromAccountInitialBalance.subtract(amount), fromAccountFinalBalance);
        assertEquals(toAccountInitialBalance.add(amount), toAccountFinalBalance);
    }
}
