package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.domain.UserCredentials;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredentials, String>{
	
}
