package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.UserCredentials;

public interface UserCredentialService {

	public List<UserCredentials> findAll();
	public UserCredentials save(UserCredentials usercredentials);
	public UserCredentials findOne(Long id);
	
}
