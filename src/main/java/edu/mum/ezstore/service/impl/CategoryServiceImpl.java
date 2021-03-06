package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.Category;
import edu.mum.ezstore.repository.CategoryRepository;
import edu.mum.ezstore.service.CategoryService;
import edu.mum.ezstore.validator.AnnotationValidator;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}

	@AnnotationValidation
	public Category save(Category category){
		return categoryRepository.save(category);
	}

	public Category findOne(Long id){
		Category category=categoryRepository.findOne(id);
		if (category == null) throw new ObjectNotFoundException("Category Id: " + id);
		return category;
	}
}
