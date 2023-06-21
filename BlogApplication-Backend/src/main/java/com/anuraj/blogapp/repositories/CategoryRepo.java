package com.anuraj.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuraj.blogapp.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
