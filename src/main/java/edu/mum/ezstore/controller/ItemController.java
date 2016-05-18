package edu.mum.ezstore.controller;

import java.util.List;

import com.egen.exhandle.exception.AuthorizationException;
import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.domain.Category;
import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.service.ItemService;
import edu.mum.ezstore.service.UserService;


@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

    @RequestMapping(value="/item/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> createItem(@RequestBody Item item) {
		User currentUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		item.setSeller(currentUser);

		Item savedItem = itemService.save(item);
		return new ResponseEntity<Item>(savedItem, HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/item/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> updateItem(@RequestBody Item item) {
		User currentUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		item.setSeller(currentUser);

		Item itemOld = itemService.findOne(item.getId());
		if (itemOld == null) throw new ObjectNotFoundException();

		if (itemOld.getSeller().getId() != currentUser.getId())
			throw new AuthorizationException();

		Item savedItem = itemService.save(item);
		return new ResponseEntity<Item>(savedItem, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/item/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items= itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);    
    }
    
    @RequestMapping("/item/get/{id}")
	public Item getItemById(@PathVariable("id") Long id) {
		Item item=itemService.findOne(id);
		if(item==null) throw new ObjectNotFoundException();
		return item;
	}

	@RequestMapping(value = "/item/getmyitem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> getMyItems() {
		User currentUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Item> items = itemService.findByUser(currentUser);
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/item/getitem/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> getItemBySeller(@PathVariable("userName") String userName) {
		User user = userService.findByUserName(userName);
		if (user == null) throw new ObjectNotFoundException();
		List<Item> items = itemService.findByUser(user);
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@RequestMapping(value = "/item/getbycategory/{catId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable("catId") Long catId) {
		List<Item> items = itemService.findByCategory(catId);
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
}
