// {!begin top}
package cz.vut.gja.ragulin.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.vut.gja.ragulin.persistence.domain.Order;

public interface OrdersRepository extends CrudRepository<Order, String> {
	Order findById(int key);

	// {!end top}

	// {!begin query}
	@Query(value = "select no.* from NOODLE_ORDERS no where no.ORDER_ID in (select ID from ORDER_ORDER_ITEMS where MENU_ID = :menuId)", nativeQuery = true)
	List<Order> findOrdersContaining(@Param("menuId") String menuId);
	// {!end query}
}