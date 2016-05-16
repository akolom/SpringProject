package edu.mum.ezstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.ezstore.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
