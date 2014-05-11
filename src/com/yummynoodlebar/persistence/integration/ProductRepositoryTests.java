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
import com.yummynoodlebar.persistence.domain.Product;
import com.yummynoodlebar.persistence.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
// {!begin transactional}
@Transactional
@TransactionConfiguration(defaultRollback = true)
// {!end transactional}
public class ProductRepositoryTests {

	@Autowired
	ProductRepository productsRepository;

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {
		String prodkey = UUID.randomUUID().toString();
		Product product = new Product(prodkey);
		product.setProductName("bike");
		product.setProductPrice(3.0);
		productsRepository.save(product);
		Product retrievedProduct = productsRepository.findById(prodkey);

		assertNotNull(retrievedProduct);
		assertEquals(prodkey, retrievedProduct.getId());
		assertEquals("bike", retrievedProduct.getProductName());
	}

}
