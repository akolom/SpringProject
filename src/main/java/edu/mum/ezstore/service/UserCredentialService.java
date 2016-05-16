package edu.mum.ezstore.service;



import edu.mum.ezstore.domain.UserCredentials;

public interface UserCredentialService {
	public UserCredentials findByUserName(String username);
}
