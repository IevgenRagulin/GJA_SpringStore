// {!begin top}
package com.yummynoodlebar.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.yummynoodlebar.persistence.domain.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
	Product findById(String key);
	// {!end top}

}