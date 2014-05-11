package com.yummynoodlebar.persistence.integration;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.domain.Product;
import com.yummynoodlebar.persistence.repository.CustomersRepository;
import com.yummynoodlebar.persistence.repository.OrdersRepository;
import com.yummynoodlebar.persistence.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
// {!begin transactional}
@Transactional
@TransactionConfiguration(defaultRollback = true)
// {!end transactional}
public class OrdersRepositoryIntegrationTests {

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	ProductRepository productsRepository;

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {
		String key = UUID.randomUUID().toString();
		String custkey = UUID.randomUUID().toString();

		Customer customer = new Customer(custkey);
		customer.setCity("KYIV");
		customer.setEmail("email@gmail");
		customer.setName("vasyl vasyltsiv");
		customersRepository.save(customer);
		Map<Product, Integer> items = new HashMap<Product, Integer>();
		Order order = new Order(customer, items, key);
		order.setDateTimeOfSubmission(new Date());

		order.setOrderItems(items);

		ordersRepository.save(order);

		Order retrievedOrder = ordersRepository.findById(key);

		assertNotNull(retrievedOrder);
		assertEquals(key, retrievedOrder.getId());
	}

	@Test
	public void thatCustomerIsSavedWithOrder() {
		String key = UUID.randomUUID().toString();
		String custkey = UUID.randomUUID().toString();

		Map<Product, Integer> orderItems = null;
		Customer customer = new Customer(custkey);
		customer.setEmail("email@gmail");
		customer.setName("vasyl vasyltsiv");
		customer.setCity("KYIV");
		customersRepository.save(customer);
		Order order = new Order(customer, orderItems, key);

		ordersRepository.save(order);

		Order retrievedOrder = ordersRepository.findById(key);

		assertNotNull(retrievedOrder);
		assertEquals("KYIV", retrievedOrder.getCustomer().getCity());
	}

	@Test
	public void thatProductsAreSavedWithOrder() {
		String key = UUID.randomUUID().toString();
		String custkey = UUID.randomUUID().toString();
		String prodkey = UUID.randomUUID().toString();

		Customer customer = new Customer(custkey);
		customer.setEmail("email@gmail");
		customer.setName("vasyl vasyltsiv");
		customersRepository.save(customer);

		Map<Product, Integer> items = new HashMap<Product, Integer>();
		Order order = new Order(customer, items, key);
		order.setDateTimeOfSubmission(new Date());

		Product product1 = new Product(prodkey);
		product1.setProductName("bike with pedals");
		product1.setProductPrice(5.0);
		items.put(product1, 3);
		order.setOrderItems(items);

		productsRepository.save(product1);
		ordersRepository.save(order);

		Order retrievedOrder = ordersRepository.findById(key);
		Map<Product, Integer> retrieveditems = retrievedOrder.getOrderItems();

		assertNotNull(retrievedOrder);
		assertEquals(key, retrievedOrder.getId());
		assertEquals(true, retrievedOrder.getOrderItems().containsValue(3));

	}
}
