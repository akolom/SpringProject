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

import edu.mum.ezstore.domain.Item;
import edu.mum.ezstore.domain.UserCredentials;
import edu.mum.ezstore.service.UserCredentialService;

@RestController
@RequestMapping("/usercredentials")
public class UserCredentialsController {
	@Autowired UserCredentialService usercredentialservice;
	
	@RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<UserCredentials> createUserCredential(@RequestBody UserCredentials usercredentials){
		UserCredentials savedUserCredentials = usercredentialservice.save(usercredentials);
		return new ResponseEntity<UserCredentials>(savedUserCredentials , HttpStatus.CREATED);
	}

	 @RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<UserCredentials> updateUserCredential(@RequestBody UserCredentials userCredentials) {
	    	UserCredentials savedUserCredential = usercredentialservice.save(userCredentials);
			return new ResponseEntity<UserCredentials>(savedUserCredential, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserCredentials>> getAllUserCredentials() {
        List<UserCredentials> userCredentials= usercredentialservice.findAll();
        return new ResponseEntity<>(userCredentials, HttpStatus.OK);    
    }
    
    @RequestMapping("/get/{username}")
	public UserCredentials getUserCredentialByUsername(@PathVariable("username") String username) {
		return  usercredentialservice.findByUserName(username);
	}
	
}
