package com.yummynoodlebar.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.yummynoodlebar.persistence.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, String> {
	Customer findById(String key);

}
