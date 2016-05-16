package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.Address;

public interface AddressService {

	public List<Address> findAll();
	public Address save(Address address);
	public Address findOne(Long id);
}
