package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.Order;
import edu.mum.ezstore.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl {

	@Autowired
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