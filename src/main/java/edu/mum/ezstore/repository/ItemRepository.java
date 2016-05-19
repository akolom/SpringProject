package edu.mum.ezstore.repository;

import edu.mum.ezstore.domain.Category;
import edu.mum.ezstore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findBySeller(User user);

    @Query("select i from Item i inner join i.categories c where c.id = :id")
    public List<Item> findByCategory(@Param("id") Long id);

    @Query("select i from Item i where i.name like %:name%")
    public List<Item> findByName(@Param("name") String name);
}
