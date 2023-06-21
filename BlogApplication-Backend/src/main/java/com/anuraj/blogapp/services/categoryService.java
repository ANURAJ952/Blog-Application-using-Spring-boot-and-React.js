package com.anuraj.blogapp.services;

import java.util.List;

import com.anuraj.blogapp.payloads.CategoryDto;

public interface categoryService {
    
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
	void deleteCategory(int categoryId);
	CategoryDto getCategory(int categoryId);
	List<CategoryDto> getAllCategory();
}
