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

import edu.mum.ezstore.domain.Order;
import edu.mum.ezstore.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
    private OrderService OrderService;

    @RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order savedOrder = OrderService.save(order);
		return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
       	Order savedOrder = OrderService.save(order);
   		return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
   	}
       
       @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
       public ResponseEntity<List<Order>> getAllOrders() {
           List<Order> orders=OrderService.findAll();
           return new ResponseEntity<>(orders, HttpStatus.OK);    
       }
       
       @RequestMapping("/get/{id}")
   	public Order getOrderById(@PathVariable("id") Long id) {
   		return  OrderService.findOne(id);
    
       } 
}
