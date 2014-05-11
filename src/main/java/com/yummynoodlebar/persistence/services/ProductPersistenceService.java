package com.yummynoodlebar.persistence.services;

import java.util.List;

import com.yummynoodlebar.persistence.domain.Product;

public interface ProductPersistenceService {
	public List<Product> requestAllProducts();

	public void addProduct(Product product);

	public Product getProductById(String productId);

}
