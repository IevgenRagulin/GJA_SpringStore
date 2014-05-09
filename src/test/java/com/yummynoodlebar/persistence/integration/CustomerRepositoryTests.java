package com.yummynoodlebar.persistence.integration;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.yummynoodlebar.config.JPAConfiguration;
import com.yummynoodlebar.persistence.domain.Customer;
import com.yummynoodlebar.persistence.repository.CustomersRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
// {!begin transactional}
@Transactional
@TransactionConfiguration(defaultRollback = true)
// {!end transactional}
public class CustomerRepositoryTests {

	@Autowired
	CustomersRepository customersRepository;

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {
		String custkey = UUID.randomUUID().toString();
		Customer customer = new Customer(custkey);
		customer.setEmail("email at gmail");
		customer.setName("user name");
		customer.setCity("Dnipropertovsk");
		customersRepository.save(customer);
		String key = customer.getId();
		Customer retrievedCustomer = customersRepository.findById(key);

		assertNotNull(retrievedCustomer);
		assertEquals(key, retrievedCustomer.getId());
		assertEquals("Dnipropertovsk", retrievedCustomer.getCity());
	}

}
