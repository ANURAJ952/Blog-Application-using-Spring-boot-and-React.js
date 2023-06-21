package com.anuraj.blogapp.services.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anuraj.blogapp.payloads.CategoryDto;
import com.anuraj.blogapp.repositories.CategoryRepo;
import com.anuraj.blogapp.services.categoryService;
import com.anuraj.blogapp.entities.Category;
import com.anuraj.blogapp.exception.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements categoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepo.save(cat);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category updateCategory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "category Id", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category deleteCategory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "category Id", categoryId));
		this.categoryRepo.delete(deleteCategory);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		Category deleteCategory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "category Id", categoryId));
		return this.modelMapper.map(deleteCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category>Categorys = this.categoryRepo.findAll();
		List<CategoryDto> categoryDtos = Categorys.stream().map(Category->this.modelMapper.map(Category, CategoryDto.class)).collect(Collectors
				.toList());
		return categoryDtos;
	}
}
