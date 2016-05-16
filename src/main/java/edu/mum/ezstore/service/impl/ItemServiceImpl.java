package edu.mum.ezstore.service.impl;

import java.util.List;

import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.repository.ItemRepository;

public class ItemServiceImpl {

	ItemRepository itemRepository;
	
	public List<Item> findAll(){
		return itemRepository.findAll();
	}
	public Item save(Item item){
		return itemRepository.save(item);
	}
	public Item findone(Long id){
		return itemRepository.findOne(id);
	}
}
