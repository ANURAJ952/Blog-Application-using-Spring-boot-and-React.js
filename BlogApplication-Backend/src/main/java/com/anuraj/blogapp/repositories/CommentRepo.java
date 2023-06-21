package com.anuraj.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anuraj.blogapp.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

	
}
