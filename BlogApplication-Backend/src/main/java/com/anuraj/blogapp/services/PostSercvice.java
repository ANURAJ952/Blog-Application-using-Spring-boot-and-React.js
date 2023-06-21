package com.anuraj.blogapp.services;

import java.util.List;
import com.anuraj.blogapp.payloads.PostDto;
import com.anuraj.blogapp.payloads.PostResponse;

public interface PostSercvice {
   
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePostById(int postId);
	
	PostResponse getAllPost(int pageNumber, int pageSize,String sortBy);
	
	PostDto getPostById(int postId);
	
	PostResponse getPostByCategory(int categoryId,int pageNumber, int pageSize,String sortBy);
	
	PostResponse getPostByUser(int id,int pageNumber, int pageSize,String sortBy);

	List<PostDto> searchPosts(String keyword);
}
