package edu.mum.ezstore.service.impl;

import java.util.List;

import edu.mum.ezstore.domain.Address;
import edu.mum.ezstore.repository.AddressRepository;

public class AddressServiceImpl {

	AddressRepository addressRepository;
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	public Address save(Address address){
		return addressRepository.save(address);
	}
	public Address findOne(Long id){
		return addressRepository.findOne(id);
	}
}
