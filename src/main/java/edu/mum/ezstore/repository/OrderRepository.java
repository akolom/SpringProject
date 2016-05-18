package edu.mum.ezstore.repository;

import edu.mum.ezstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.ItemOrder;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ItemOrder, Long>{
    public List<ItemOrder> findByBuyer(User buyer);
}
