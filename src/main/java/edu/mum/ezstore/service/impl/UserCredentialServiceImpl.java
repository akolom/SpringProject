package edu.mum.ezstore.service.impl;

import java.util.List;

import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.repository.UserRepository;
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

	@AnnotationValidation
	public UserCredentials save(UserCredentials usercredentials) {
		return userCredentialsRepository.save(usercredentials);
	}

	@Override
	public UserCredentials findByUserName(String username) {
		UserCredentials userCredentials = userCredentialsRepository.findOne(username);
		if (userCredentials == null) throw new ObjectNotFoundException("User credential: " + username);
		return userCredentials;
	}
}
