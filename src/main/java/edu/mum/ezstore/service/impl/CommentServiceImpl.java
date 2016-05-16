package edu.mum.ezstore.service.impl;

import java.util.List;

import edu.mum.ezstore.domain.Comment;
import edu.mum.ezstore.repository.CommentRepository;

public class CommentServiceImpl {

	CommentRepository commentRepository;
	
	public List<Comment> findAll(){
		return commentRepository.findAll();
	}
	public Comment save(Comment comment){
		return commentRepository.save(comment);
	}
	public Comment findOne(Long id){
		return commentRepository.findOne(id);
	}
	
}
