package edu.mum.ezstore.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
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

	@AnnotationValidation
	public Comment save(Comment comment){
		return commentRepository.save(comment);
	}
	public Comment findOne(Long id){
		Comment comment = commentRepository.findOne(id);
		if (comment == null) throw new ObjectNotFoundException("Comment Id: " + id);
		return comment;
	}
	
}
