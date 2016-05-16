package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
