package com.examplebank.OBO.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplebank.OBO.entities.Client;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByDateOfBirthGreaterThanEqual(LocalDate dateOfBirth);
    List<Client> findByPhoneNumbersContaining(String phone);
    List<Client> findByNameStartingWith(String name);
    List<Client> findByEmailsContaining(String email);
}

