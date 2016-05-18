package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import edu.mum.ezstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.ItemOrder;
import edu.mum.ezstore.repository.OrderRepository;
import edu.mum.ezstore.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	public List<ItemOrder> findAll(){
		return orderRepository.findAll();
	}

	@AnnotationValidation
	public ItemOrder save(ItemOrder itemOrder){
		return orderRepository.save(itemOrder);
	}
	public ItemOrder findOne(Long order_ID){
		return orderRepository.findOne(order_ID);
	}

	@Override
	public List<ItemOrder> findByBuyer(User buyer) {
		return orderRepository.findByBuyer(buyer);
	}
}
