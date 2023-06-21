package com.anuraj.blogapp.services;

import java.util.List;

import com.anuraj.blogapp.payloads.UserDto;

public interface UserService {
	
    UserDto RegisterUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserId(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
}
