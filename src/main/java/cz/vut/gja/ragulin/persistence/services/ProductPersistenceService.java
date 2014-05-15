package cz.vut.gja.ragulin.persistence.services;

import java.util.List;

import cz.vut.gja.ragulin.persistence.domain.Product;

public interface ProductPersistenceService {
	public List<Product> requestAllProducts();

	public void addProduct(Product product);

	public Product getProductById(int productId);

}
