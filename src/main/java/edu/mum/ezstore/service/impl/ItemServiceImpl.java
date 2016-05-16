package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.repository.ItemRepository;
import edu.mum.ezstore.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
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
