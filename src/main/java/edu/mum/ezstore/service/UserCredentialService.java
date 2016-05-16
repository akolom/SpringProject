package edu.mum.ezstore.service;


import java.util.List;

import edu.mum.ezstore.domain.UserCredentials;

public interface UserCredentialService {
	public UserCredentials findByUserName(String username);
	public List<UserCredentials> findAll();
	public UserCredentials save(UserCredentials usercredentials);
	
}
