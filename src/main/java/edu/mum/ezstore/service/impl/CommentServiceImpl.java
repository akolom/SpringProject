package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.Comment;
import edu.mum.ezstore.repository.CommentRepository;
import edu.mum.ezstore.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
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
