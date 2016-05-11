package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<String, Customer>{

}
