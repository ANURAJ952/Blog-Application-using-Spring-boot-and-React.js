package com.anuraj.blogapp.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.anuraj.blogapp.entities.Category;
import com.anuraj.blogapp.entities.Post;
import com.anuraj.blogapp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
      List<Post> findByUser(User user);
      List<Post> findByCategory(Category category);
	  Page<Post> findByCategory(Category category, Pageable p);
	  Page<Post> findByUser(User user, Pageable p);
	  List<Post> findByTitleContaining(String keywords);
	  
}
