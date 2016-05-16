package edu.mum.ezstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.UserCredentials;
import edu.mum.ezstore.repository.UserCredentialRepository;
import edu.mum.ezstore.service.UserCredentialService;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;


	@Override
	public UserCredentials findByUserName(String username) {		
		return userCredentialRepository.findOne(username);
	}
}
