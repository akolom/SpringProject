package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.UserCredentials;
import edu.mum.ezstore.repository.UserCredentialsRepository;

@Service
@Transactional
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
