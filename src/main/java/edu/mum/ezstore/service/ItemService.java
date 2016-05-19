package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.domain.User;

public interface ItemService {

	public List<Item> findAll();
	public Item save(Item item);
	public Item findOne(Long id);
	public List<Item> findByUser(User user);
	public List<Item> findByCategory(Long id);
	public List<Item> findByName(String name);


}
