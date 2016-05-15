package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
