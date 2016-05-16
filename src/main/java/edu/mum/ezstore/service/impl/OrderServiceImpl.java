package edu.mum.ezstore.service.impl;

import java.util.List;

import edu.mum.ezstore.domain.Order;
import edu.mum.ezstore.repository.OrderRepository;

public class OrderServiceImpl {

	OrderRepository orderRepository;
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	public Order save(Order order){
		return orderRepository.save(order);
	}
	public Order findOne(Long order_ID){
		return orderRepository.findOne(order_ID);
	}
}
