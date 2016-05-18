package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import edu.mum.ezstore.domain.Address;
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
		for (Address address : user.getAddress()){
			address.setUser(user);
		}
		return userRepository.save(user);
	}

	public User findOne(Long id){
		User user = userRepository.findOne(id);
		if (user == null) throw new ObjectNotFoundException("User id: " + id);
		return user;
	}

	public User findByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		if (user == null) throw new ObjectNotFoundException("Username: " + userName);
		return user;
	}
}
