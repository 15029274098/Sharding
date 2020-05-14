package com.mayikt.controller;

import java.util.ArrayList;
import java.util.List;

import com.mayikt.entity.Order;
import com.mayikt.mapper.OrderMapper;
import com.mayikt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mayikt.entity.OrderEntity;

@RestController
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private com.mayikt.mapper.OrderMapper orderMapper;

	// 查询所有的订单信息
	@RequestMapping("/getOrderAll")
	public List<Order> getOrderAll() {
		return  orderMapper.getOrderAll();
	}


	// 增加
	@RequestMapping("/inserOrder")
	public String inserOrder() {
		for (int i = 0; i < 10; i++) {
			OrderEntity order = new OrderEntity();
			order.setOrderId((long) i);
			order.setUserId((long) i);
			orderRepository.save(order);
		}
		return "success";
	}

}
