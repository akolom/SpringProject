package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.ItemOrder;
import edu.mum.ezstore.domain.User;

public interface OrderService {
	public List<ItemOrder> findAll();
	public ItemOrder save(ItemOrder itemOrder);
	public ItemOrder findOne(Long order_ID);
	public List<ItemOrder> findByBuyer(User buyer);

}
