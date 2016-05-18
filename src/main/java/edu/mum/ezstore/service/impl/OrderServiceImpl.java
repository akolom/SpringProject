package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.egen.exhandle.exception.BusinessException;
import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.service.ItemService;
import edu.mum.ezstore.service.UserService;
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

	@Autowired
	ItemService itemService;

	@Autowired
	UserService userService;
	
	public List<ItemOrder> findAll(){
		return orderRepository.findAll();
	}

	@AnnotationValidation
	public ItemOrder save(ItemOrder itemOrder){

		// check if item is existed or not
		Item item = itemService.findOne(itemOrder.getItem().getId());

		// check if item is sold or not
		if (item.getItemOrder() != null) throw new BusinessException("Item is already sold");

		// set item to itemOrder
		itemOrder.setItem(item);
		// save
		ItemOrder savedOrder = orderRepository.save(itemOrder);

		item.setItemOrder(savedOrder);
		itemService.save(item);

		return savedOrder;
	}
	public ItemOrder findOne(Long order_ID){
		ItemOrder itemOrder = orderRepository.findOne(order_ID);
		if (itemOrder == null) throw new ObjectNotFoundException("orderId:"+ order_ID);
		return itemOrder;
	}

	@Override
	public List<ItemOrder> findByBuyer(User buyer) {
		return orderRepository.findByBuyer(buyer);
	}
}
