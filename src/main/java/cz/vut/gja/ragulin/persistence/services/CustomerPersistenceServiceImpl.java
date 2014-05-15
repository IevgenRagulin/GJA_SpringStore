package cz.vut.gja.ragulin.persistence.services;


import cz.vut.gja.ragulin.persistence.domain.Customer;
import cz.vut.gja.ragulin.persistence.repository.CustomersRepository;

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
