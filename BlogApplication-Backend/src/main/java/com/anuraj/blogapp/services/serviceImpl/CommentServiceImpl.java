package com.anuraj.blogapp.services.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuraj.blogapp.entities.Comment;
import com.anuraj.blogapp.entities.Post;
import com.anuraj.blogapp.entities.User;
import com.anuraj.blogapp.exception.ResourceNotFoundException;
import com.anuraj.blogapp.payloads.CommentDto;
import com.anuraj.blogapp.repositories.CommentRepo;
import com.anuraj.blogapp.repositories.PostRepo;
import com.anuraj.blogapp.repositories.UserRepo;
import com.anuraj.blogapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId, int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId",postId));
		
		Comment comment =  this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		comment.setUser(user);
		
		Comment SavedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(SavedComment, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "comment",commentId));
		
		this.commentRepo.delete(comment);
         
	}

}
