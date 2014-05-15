package cz.vut.gja.ragulin.web.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import cz.vut.gja.ragulin.persistence.domain.Customer;
import cz.vut.gja.ragulin.persistence.domain.Order;
import cz.vut.gja.ragulin.persistence.domain.Product;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket {
	private Map<Integer, Product> items = new HashMap<Integer, Product>();
	private Map<Integer, Integer> numberOfItems = new HashMap<Integer, Integer>();

	public Basket() {
	}

	public Basket(Map<Integer, Product> items,
			Map<Integer, Integer> numberOfItems) {
		this.items = items;
		this.numberOfItems = numberOfItems;
	}

	public Product add(Product item) {
		// increment number of products counter
		if (numberOfItems.containsKey(item.getId())) {
			Integer prevValue = numberOfItems.get(item.getId());
			numberOfItems.put(item.getId(), ++prevValue);
		} else {
			numberOfItems.put(item.getId(), 1);
		}
		items.put(item.getId(), item);
		return item;
	}

	public void delete(int key) {
		items.remove(key);
		numberOfItems.remove(key);
	}

	public Product findById(int key) {
		for (Product item : items.values()) {
			if (item.getId() == key) {
				return item;
			}
		}
		return null;
	}

	public List<Product> findAll() {
		return new ArrayList<Product>(items.values());
	}

	public List<Integer> getNumbersOfProducts() {
		return new ArrayList<Integer>(numberOfItems.values());
	}

	public List<Product> getItems() {
		return findAll();
	}

	public Map<Integer, Integer> getNumberOfItems() {
		return numberOfItems;
	}

	public Integer getNumberOfItemsForProduct(int productId) {
		return numberOfItems.get(productId);
	}

	public int getSize() {
		Collection<Integer> numbersOfProducts = numberOfItems.values();
		int sum = 0;
		Iterator<Integer> iterator = numbersOfProducts.iterator();
		while (iterator.hasNext()) {
			int value = iterator.next();
			sum += value;
		}
		return sum;
	}

	public void clear() {
		items = new HashMap<Integer, Product>();
		numberOfItems = new HashMap<Integer, Integer>();
	}

	public Order createOrderDetailsWithCustomerInfo(Customer customer) {
		return new Order(customer, getMapProductNumberOfProducts());
	}

	public Map<Product, Integer> getMapProductNumberOfProducts() {
		Map<Product, Integer> orderItems = new HashMap<>();// product, number of
		Iterator<Entry<Integer, Product>> it = items.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Product> pairs = (Map.Entry<Integer, Product>) it
					.next();
			Integer number = numberOfItems.get(pairs.getKey());
			orderItems.put(pairs.getValue(), number);
		}
		return orderItems;
	}

}
