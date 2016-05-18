package edu.mum.ezstore.controller;

import java.util.List;

import com.egen.exhandle.exception.BusinessException;
import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.service.ItemService;
import edu.mum.ezstore.service.UserService;
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

import edu.mum.ezstore.domain.ItemOrder;
import edu.mum.ezstore.service.OrderService;

@RestController
public class OrderController {

	@Autowired
    private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

    @RequestMapping(value="/order/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemOrder> createOrder(@RequestBody ItemOrder itemOrder) {
		// check if logged in user is existed or not (buyer is the logged in user)
		String strBuyerUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		User buyer = userService.findByUserName(strBuyerUserName);
		itemOrder.setBuyer(buyer);

		ItemOrder savedItemOrder = orderService.save(itemOrder);
		return new ResponseEntity<ItemOrder>(savedItemOrder, HttpStatus.CREATED);
	}
    
//    @RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
//   	public ResponseEntity<ItemOrder> updateOrder(@RequestBody ItemOrder itemOrder) {
//       	ItemOrder savedItemOrder = orderService.save(itemOrder);
//   		return new ResponseEntity<ItemOrder>(savedItemOrder, HttpStatus.OK);
//   	}
       
   @RequestMapping(value = "/order/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<ItemOrder>> getAllOrders() {
	   List<ItemOrder> itemOrders =orderService.findAll();
	   return new ResponseEntity<>(itemOrders, HttpStatus.OK);
   }

	@RequestMapping(value = "/order/getmyorder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemOrder>> getMyOrders() {
		User currentUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		List<ItemOrder> itemOrders = orderService.findByBuyer(currentUser);
		return new ResponseEntity<>(itemOrders, HttpStatus.OK);
	}

	@RequestMapping("/order/get/{id}")
   	public ItemOrder getOrderById(@PathVariable("id") Long id) {
		ItemOrder itemOrder=orderService.findOne(id);
   		return  itemOrder;
    
	}

	@RequestMapping(value = "/admin/order/getorder/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ItemOrder>> getOrderByBuyer(@PathVariable("userName") String userName) {
		User user = userService.findByUserName(userName);
		List<ItemOrder> itemOrders = orderService.findByBuyer(user);
		return new ResponseEntity<>(itemOrders, HttpStatus.OK);
	}
}
