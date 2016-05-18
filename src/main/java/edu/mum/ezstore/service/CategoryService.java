package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.Category;

public interface CategoryService {

	public List<Category> findAll();
	public Category save(Category category);
	public Category findOne(Long id);
	public Category getOne(Long id);
	
}
