package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.Order;
import edu.mum.ezstore.domain.User;

public interface OrderService {

	public List<Order> findAll();
	public Order save(Order user);
	public Order findOne(Long id);	
}
