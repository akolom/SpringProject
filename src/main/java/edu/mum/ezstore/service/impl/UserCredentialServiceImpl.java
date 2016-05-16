package edu.mum.ezstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.ezstore.domain.UserCredentials;
import edu.mum.ezstore.service.UserCredentialService;

import edu.mum.ezstore.repository.UserCredentialsRepository;

@Service
@Transactional
public class UserCredentialServiceImpl implements UserCredentialService {

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;

	public List<UserCredentials> findAll() {
		return userCredentialsRepository.findAll();
	}

	public UserCredentials save(UserCredentials usercredentials) {
		return userCredentialsRepository.save(usercredentials);

	}

	@Override
	public UserCredentials findByUserName(String username) {
		return userCredentialsRepository.findOne(username);
	}
}
