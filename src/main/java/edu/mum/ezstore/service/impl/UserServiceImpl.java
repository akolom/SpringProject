package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.repository.UserRepository;
import edu.mum.ezstore.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	@AnnotationValidation
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User findOne(Long id){
		return userRepository.findOne(id);
	}
}
