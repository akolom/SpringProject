package edu.mum.ezstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.service.ItemService;


@RestController
@RequestMapping("/item")
public class ItemController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

//    @Autowired
//    private ItemService itemService;
//
//    @RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Item> createItem(@RequestBody Item item) {
//    	LOG.info(">>>>>>>>>>>>>>> add an item >>>>>>>>>>");
//    		
//		Item savedItem = itemService.save(item);
//		return new ResponseEntity<Item>(savedItem, HttpStatus.CREATED);
//	}
//    
//    @RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Item> updateItem(@RequestBody Item item) {
//    	LOG.info(">>>>>>>>>>>>>>> add an item >>>>>>>>>>");
//    	Item savedItem = itemService.save(item);
//		return new ResponseEntity<Item>(savedItem, HttpStatus.OK);
//	}
//    
//    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Item>> getAllItems() {
//
//        LOG.info(">>>>>>>>>>>>>>> get all items >>>>>>>>>>");
//        List<Item> users= itemService.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);    
//    }
//    
//    @RequestMapping("/get/{id}")
//	public Item getItemById(@PathVariable("id") Long id) {
//		return  itemService.findone(id);
// 
//	}
}
