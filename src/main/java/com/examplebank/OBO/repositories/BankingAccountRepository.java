package com.examplebank.OBO.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplebank.OBO.entities.BankingAccount;

@Repository
public interface BankingAccountRepository extends JpaRepository<BankingAccount, Long> {
}

