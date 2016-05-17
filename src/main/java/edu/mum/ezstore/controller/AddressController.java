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

import edu.mum.ezstore.domain.Address;
import edu.mum.ezstore.service.AddressService;
import edu.mum.ezstore.service.ItemService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		{
			Address savedAddress = addressService.save(address);
			return new ResponseEntity<Address>(savedAddress, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) {

		Address savedAddress = addressService.save(address);
		return new ResponseEntity<Address>(savedAddress, HttpStatus.OK);
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> getAllItems() {
		List<Address> addresses = addressService.findAll();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@RequestMapping(value= "/get/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public Address getAddressById(@PathVariable("id") Long id) {
		return addressService.findOne(id);
		
	}
	
}
