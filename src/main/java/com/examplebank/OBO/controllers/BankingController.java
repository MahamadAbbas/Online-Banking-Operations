package com.examplebank.OBO.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examplebank.OBO.services.BankingService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/banking")
public class BankingController {

    @Autowired
    private BankingService bankingService;

    @PostMapping("/transfer")
    public void transferMoney(@RequestParam Long fromAccountId,
                              @RequestParam Long toAccountId,
                              @RequestParam BigDecimal amount) {
        bankingService.transferMoney(fromAccountId, toAccountId, amount);
    }
}
