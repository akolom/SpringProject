package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from User u where u.userCredentials.username=:userName")
    public User findByUserName(@Param("userName") String userName);
}
