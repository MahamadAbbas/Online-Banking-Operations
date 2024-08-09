package com.examplebank.OBO.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.examplebank.OBO.entities.Client;
import com.examplebank.OBO.services.ClientService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PostMapping
	public Client createClient(@RequestParam String name,
			@RequestParam LocalDate dateOfBirth, @RequestParam BigDecimal initialBalance,
			@RequestParam Set<String> phoneNumbers, @RequestParam Set<String> emails) {
		return clientService.createClient(name, dateOfBirth, initialBalance, phoneNumbers, emails);
	}

	@GetMapping
	public List<Client> searchClients(@RequestParam(required = false) LocalDate dateOfBirth,
			@RequestParam(required = false) String phone, @RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		return clientService.searchClients(dateOfBirth, phone, name, email);
	}

	@PutMapping("/{id}")
	public Client updateClient(@PathVariable Long id,
			@RequestParam Set<String> phoneNumbers, @RequestParam Set<String> emails) {
		return clientService.updateClient(id, phoneNumbers, emails);
	}
}