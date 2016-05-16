package edu.mum.ezstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.ezstore.domain.UserCredentials;
import edu.mum.ezstore.repository.UserCredentialsRepository;
public class UserCredentialServiceImpl {

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
	public List<UserCredentials> findAll(){
		return userCredentialsRepository.findAll();
	}
	public UserCredentials save(UserCredentials usercredentials){
		return userCredentialsRepository.save(usercredentials);
		
	}
	public UserCredentials findOne(String userName){
		return userCredentialsRepository.findOne(userName);
	}
}
