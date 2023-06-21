package com.anuraj.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuraj.blogapp.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
