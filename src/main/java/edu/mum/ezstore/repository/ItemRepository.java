package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
