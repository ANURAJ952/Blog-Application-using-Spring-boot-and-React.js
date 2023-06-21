package com.anuraj.blogapp.services.serviceImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anuraj.blogapp.entities.Category;
import com.anuraj.blogapp.entities.Post;
import com.anuraj.blogapp.entities.User;
import com.anuraj.blogapp.exception.ResourceNotFoundException;
import com.anuraj.blogapp.payloads.PostDto;
import com.anuraj.blogapp.payloads.PostResponse;
import com.anuraj.blogapp.repositories.CategoryRepo;
import com.anuraj.blogapp.repositories.PostRepo;
import com.anuraj.blogapp.repositories.UserRepo;
import com.anuraj.blogapp.services.PostSercvice;

@Service
public class PostServiceImpl implements PostSercvice {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName(postDto.getImageName());
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setAddedDate(new Date());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePostById(int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy) {

		Pageable p = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPostByCategory(int categoryId, int pageNumber, int pageSize,String sortBy) {
	    Category category = this.categoryRepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));
	    
	    Pageable p = PageRequest.of(pageNumber, pageSize);
	    Page<Post> pagePost = this.postRepo.findByCategory(category,p);
	    List<Post> allPosts = pagePost.getContent();
	    List<PostDto> postDtos = allPosts.stream()
	            .map((post) -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());
	    
	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(postDtos);
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setTotalElements(pagePost.getTotalElements());
	    postResponse.setTotalPages(pagePost.getTotalPages());
	    postResponse.setLastPage(pagePost.isLast());

	    return postResponse;
	}


	@Override
	public PostResponse getPostByUser(int id, int pageNumber, int pageSize,String sortBy) {
	    User user = this.userRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("user", "user Id", id));

	    Pageable p = PageRequest.of(pageNumber, pageSize);
	    Page<Post> pagePost = this.postRepo.findByUser(user, p);
	    List<Post> allPosts = pagePost.getContent();
	    List<PostDto> postDtos = allPosts.stream()
	            .map((post) -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(postDtos);
	    postResponse.setPageSize(pagePost.getSize());
	    postResponse.setPageNumber(pagePost.getNumber());
	    postResponse.setTotalElements(pagePost.getTotalElements());
	    postResponse.setTotalPages(pagePost.getTotalPages());
	    postResponse.setLastPage(pagePost.isLast());
	    
	    return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post>posts = this.postRepo.findByTitleContaining(keyword);
		 List<PostDto> postDtos = posts.stream()
		            .map((post) -> this.modelMapper.map(post, PostDto.class))
		            .collect(Collectors.toList());
		 return postDtos;

	}
}
