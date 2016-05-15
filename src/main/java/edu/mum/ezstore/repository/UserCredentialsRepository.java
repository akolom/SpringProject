package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.UserCredentials;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String>{

}
