package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.Address;
import edu.mum.ezstore.repository.AddressRepository;
import edu.mum.ezstore.service.AddressService;
import edu.mum.ezstore.validator.AnnotationValidator;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	
	@AnnotationValidation
	public Address save(Address address){
		return addressRepository.save(address);
	}
	public Address findOne(Long id){
		return addressRepository.findOne(id);
	}
}
