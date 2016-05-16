package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item save(Item item);
	public Item findone(Long id);
	
}
