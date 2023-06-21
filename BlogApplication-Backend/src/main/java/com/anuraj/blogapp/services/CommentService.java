package com.anuraj.blogapp.services;

import com.anuraj.blogapp.payloads.CommentDto;

public interface CommentService {

	void deleteComment(Integer commentId);
	CommentDto createComment(CommentDto commentDto, int postId, int userId);
}
