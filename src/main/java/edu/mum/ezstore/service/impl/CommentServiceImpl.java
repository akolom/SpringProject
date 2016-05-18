package edu.mum.ezstore.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.egen.exhandle.exception.ObjectNotFoundException;
import edu.mum.ezstore.aspect.annotation.AnnotationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.ezstore.domain.Comment;
import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.repository.CommentRepository;
import edu.mum.ezstore.service.CommentService;
import edu.mum.ezstore.service.UserService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserService userService;
	
	public List<Comment> findAll(){
		return commentRepository.findAll();
	}

	@AnnotationValidation
	public Comment save(Comment comment){
		User toUser =  userService.findOne(comment.getToUser().getId()) ;
		comment.setToUser(toUser);
		
		//insert the comment date
		Date date = new Date();
		comment.setDate(date);
		return commentRepository.save(comment);
	}
	public Comment findOne(Long id){
		Comment comment = commentRepository.findOne(id);
		if (comment == null) throw new ObjectNotFoundException("Comment Id: " + id);
		return comment;
	}
	
}
