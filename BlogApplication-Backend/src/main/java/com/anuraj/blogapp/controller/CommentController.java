package com.anuraj.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.anuraj.blogapp.payloads.ApiResponse;
import com.anuraj.blogapp.payloads.CommentDto;
import com.anuraj.blogapp.services.CommentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/comments")
public class CommentController {
   
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable int postId,@PathVariable int userId)
	{
		CommentDto commentDto = this.commentService.createComment(comment, postId, userId);
		
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/{commentId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable int commentId)
	{
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);
	}
	
	
}
