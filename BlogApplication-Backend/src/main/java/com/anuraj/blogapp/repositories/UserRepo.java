package com.anuraj.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuraj.blogapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
     Optional<User> findByEmail(String email);
}
