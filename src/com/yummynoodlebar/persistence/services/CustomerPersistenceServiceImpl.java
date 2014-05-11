package com.yummynoodlebar.persistence.services;

import com.yummynoodlebar.persistence.domain.Customer;
import com.yummynoodlebar.persistence.repository.CustomersRepository;

public class CustomerPersistenceServiceImpl implements
		CustomerPersistenceService {
	private final CustomersRepository customersRepository;

	public CustomerPersistenceServiceImpl(CustomersRepository repo) {
		this.customersRepository = repo;
	}

	@Override
	public void createCustomer(Customer customer) {
		customersRepository.save(customer);
	}
}
