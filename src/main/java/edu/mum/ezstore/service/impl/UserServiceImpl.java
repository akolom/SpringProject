package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.repository.UserRepository;
import edu.mum.ezstore.service.UserService;
import edu.mum.ezstore.validator.AnnotationValidator;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private AnnotationValidator annotationValidator;
    
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		annotationValidator.doValidate(user);
		return userRepository.save(user);
	}
	
	public User findOne(Long id){
		return userRepository.findOne(id);
	}
}
