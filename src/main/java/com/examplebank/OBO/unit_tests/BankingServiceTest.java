package com.examplebank.OBO.unit_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankingServiceTest {

    @Autowired
    private BankingService bankingService;

    @Test
    public void testTransferMoney() {
        Long fromAccountId = 1L; // Adjust IDs as needed
        Long toAccountId = 2L;
        BigDecimal amount = BigDecimal.valueOf(50);

        bankingService.transferMoney(fromAccountId, toAccountId, amount);

        // Add assertions to verify that the balances have been updated correctly
    }
}
