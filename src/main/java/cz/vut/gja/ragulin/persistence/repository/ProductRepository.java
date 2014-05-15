// {!begin top}
package cz.vut.gja.ragulin.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import cz.vut.gja.ragulin.persistence.domain.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
	Product findById(int key);

}