package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
