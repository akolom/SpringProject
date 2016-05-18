package edu.mum.ezstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.ezstore.domain.Comment;
import edu.mum.ezstore.domain.User;
import edu.mum.ezstore.service.CommentService;
import edu.mum.ezstore.service.UserService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="/add", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
		// setting fromuser as logedin user
		User currentUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		comment.setFromUser(currentUser);
		
		Comment savedComment = commentService.save(comment);
		return new ResponseEntity<Comment>(savedComment, HttpStatus.CREATED);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
		User currentUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

		comment.setFromUser(currentUser);

		Comment savedComment = commentService.update(comment);
		return new ResponseEntity<Comment>(savedComment, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments= commentService.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);    
    }
    
    @RequestMapping(value= "/get/{id}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public Comment getCommentById(@PathVariable("id") Long id) {
		return  commentService.findOne(id);
	}
}