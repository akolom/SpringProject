package edu.mum.ezstore.service;

import java.util.List;


import edu.mum.ezstore.domain.User;

public interface UserService {
	public List<User> findAll();
	public User save(User user);
	public User findOne(Long id);
	public User findByUserName(String userName);
}
