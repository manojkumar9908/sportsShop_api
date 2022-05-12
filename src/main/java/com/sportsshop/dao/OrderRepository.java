package com.sportsshop.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sportsshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByuserId(Integer userid);

	@Transactional
	@Modifying
	@Query("update sports_orders o set o.status = :status where o.id=:id")
	Optional<Order> changestatus(@Param("id") Integer id, @Param("status") String status);
}
