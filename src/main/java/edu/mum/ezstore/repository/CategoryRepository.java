package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
