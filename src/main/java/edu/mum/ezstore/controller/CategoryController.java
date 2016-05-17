package edu.mum.ezstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ezstore.domain.Category;
import edu.mum.ezstore.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;

    @RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.save(category);
		return new ResponseEntity<Category>(savedCategory, HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
    	Category savedCategory = categoryService.save(category);
		return new ResponseEntity<Category>(savedCategory, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories =categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);    
    }
    
    @RequestMapping("/get/{id}")
	public Category getCategoryById(@PathVariable("id") Long id) {
		return  categoryService.findOne(id);
 
	}

}
