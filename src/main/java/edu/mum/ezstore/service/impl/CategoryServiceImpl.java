package edu.mum.ezstore.service.impl;

import java.util.List;

import edu.mum.ezstore.domain.Category;
import edu.mum.ezstore.repository.CategoryRepository;

public class CategoryServiceImpl {

	CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	public Category save(Category category){
		return categoryRepository.save(category);
	}
	public Category findOne(Long id){
		return categoryRepository.findOne(id);
	}
}
