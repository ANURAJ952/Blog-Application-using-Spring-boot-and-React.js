package com.anuraj.blogapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anuraj.blogapp.payloads.ApiResponse;
import com.anuraj.blogapp.payloads.PostDto;
import com.anuraj.blogapp.payloads.PostResponse;
import com.anuraj.blogapp.services.FileService;
import com.anuraj.blogapp.services.PostSercvice;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostSercvice postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable int userId,@PathVariable int categoryId){
		PostDto create = this.postService.createPost(postDto, userId, categoryId);
	    return new ResponseEntity<PostDto>(create,HttpStatus.CREATED);	
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updateCategory(@RequestBody PostDto postDto,@PathVariable int postId){
		
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable int postId){
		
		this.postService.deletePostById(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable int userId,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize,
		    @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy){
		PostResponse postResponse  = this.postService.getPostByUser(userId,pageNumber,pageSize,sortBy);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable int categoryId,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize,
		    @RequestParam(value = "sortBy",defaultValue = "categoryId",required = false)String sortBy){
		PostResponse postResponse  = this.postService.getPostByCategory(categoryId,pageNumber,pageSize,sortBy);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize,
		    @RequestParam(value = "sortBy",defaultValue = "postId",required = false)String sortBy){
		PostResponse postResponse  = this.postService.getAllPost(pageNumber,pageSize,sortBy);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}/posts")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
		PostDto posts  = this.postService.getPostById(postId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords")String keyword){
		List<PostDto> result = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image
			,@PathVariable int postId) throws IOException{
		PostDto postDto = this.postService.getPostById(postId);
		String fileNmae = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileNmae);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping(value = "/posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void ServeImage(
			@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}	
}
