package com.anuraj.blogapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "postid")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
}
