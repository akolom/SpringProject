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

import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {
    	LOG.info(">>>>>>>>>>>>>>> add a user >>>>>>>>>>");
    		
		User savedUser = userService.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
    
    @RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
    	LOG.info(">>>>>>>>>>>>>>> add a user >>>>>>>>>>");
    	User savedUser = userService.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {

        LOG.info(">>>>>>>>>>>>>>> get all users >>>>>>>>>>");
        List<User> users=userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);    
    }
    
    @RequestMapping("/get/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return  userService.findOne(id);
 
	}
}
