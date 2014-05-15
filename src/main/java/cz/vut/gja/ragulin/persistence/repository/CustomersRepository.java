package cz.vut.gja.ragulin.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import cz.vut.gja.ragulin.persistence.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, String> {
	Customer findById(int key);

}
