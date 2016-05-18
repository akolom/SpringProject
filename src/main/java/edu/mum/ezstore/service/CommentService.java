package edu.mum.ezstore.service;

import java.util.List;

import edu.mum.ezstore.domain.Comment;

public interface CommentService {

	public List<Comment> findAll();
	public Comment save(Comment comment);
	public Comment update(Comment comment);
	public Comment findOne(Long id);
	
}
