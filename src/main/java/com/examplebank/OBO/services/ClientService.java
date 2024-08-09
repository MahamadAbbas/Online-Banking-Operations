package com.examplebank.OBO.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examplebank.OBO.entities.BankingAccount;
import com.examplebank.OBO.entities.Client;
import com.examplebank.OBO.repositories.BankingAccountRepository;
import com.examplebank.OBO.repositories.ClientRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BankingAccountRepository accountRepository;

	@Transactional
	public Client createClient(String name, LocalDate dateOfBirth, BigDecimal initialBalance, Set<String> phoneNumbers, Set<String> emails) {
		BankingAccount account = new BankingAccount();
		account.setBalance(initialBalance);

		Client client = new Client();
		client.setName(name);
		client.setDateOfBirth(dateOfBirth);
		client.setAccount(account);
		client.setPhoneNumbers(phoneNumbers);
		client.setEmails(emails);

		return clientRepository.save(client);
	}

	public List<Client> searchClients(LocalDate dateOfBirth, String phone, String name, String email) {
		if (dateOfBirth != null) {
			return clientRepository.findByDateOfBirthGreaterThanEqual(dateOfBirth);
		} else if (phone != null) {
			return clientRepository.findByPhoneNumbersContaining(phone);
		} else if (name != null) {
			return clientRepository.findByNameStartingWith(name);
		} else if (email != null) {
			return clientRepository.findByEmailsContaining(email);
		}
		return List.of();
	}

	@Transactional
	public Client updateClient(Long clientId, Set<String> phoneNumbers, Set<String> emails) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new RuntimeException("Client not found"));

		if (phoneNumbers != null) {
			client.setPhoneNumbers(phoneNumbers);
		}
		if (emails != null) {
			client.setEmails(emails);
		}

		return clientRepository.save(client);
	}
}
