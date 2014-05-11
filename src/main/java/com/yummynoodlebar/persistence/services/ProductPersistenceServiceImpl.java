package com.yummynoodlebar.persistence.services;

import java.util.ArrayList;
import java.util.List;

import com.yummynoodlebar.persistence.domain.Product;
import com.yummynoodlebar.persistence.repository.ProductRepository;

public class ProductPersistenceServiceImpl implements ProductPersistenceService {

	ProductRepository productRepository;

	public ProductPersistenceServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> requestAllProducts() {
		System.out.println("HEY WE ARE CALLING REQUEST ALL PRODUCTS. "
				+ productRepository);

		Iterable<Product> menuItems = productRepository.findAll();

		List<Product> productItems = new ArrayList<Product>();

		for (Product item : menuItems) {
			productItems.add(item);
		}

		return productItems;
	}

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product getProductById(String productId) {
		return productRepository.findById(productId);
	}
}
