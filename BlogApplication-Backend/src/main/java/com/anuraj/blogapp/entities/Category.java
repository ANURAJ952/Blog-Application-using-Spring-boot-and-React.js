package com.anuraj.blogapp.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor
public class Category {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	@Column(name = "title",length = 100)
	private String categoryTitle;
	@Column(name = "description")
	private String CategoryDescription;
	 @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<>();
	
}
