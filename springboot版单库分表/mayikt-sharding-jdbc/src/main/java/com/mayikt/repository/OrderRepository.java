package com.mayikt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mayikt.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
	@Query(value = "SELECT order_id ,user_id  FROM t_order  where order_id in (?1);", nativeQuery = true)
	public List<OrderEntity> findExpiredOrderState(List<String> bpIds);
}