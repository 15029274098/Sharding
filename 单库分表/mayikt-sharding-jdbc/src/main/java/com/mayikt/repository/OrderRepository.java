package com.mayikt.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mayikt.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
	@Query(value = "SELECT order_id ,user_id  FROM t_order  where order_id in (?1);", nativeQuery = true)
	public List<OrderEntity> findExpiredOrderState(List<String> bpIds);

	@Query(value = "SELECT order_id ,user_id  FROM t_order  where user_id=:userId ", nativeQuery = true)
	public List<OrderEntity> findByUserId(@Param("userId") Long userId);
}