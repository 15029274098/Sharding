package com.mayikt.entity;

import javax.persistence.*;


@Entity
@Table(name = "t_order")
public class OrderEntity {
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Id
	private Long orderId;

	private Long userId;

}
