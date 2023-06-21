package com.anuraj.blogapp.payloads;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 3, message = "username must be min of 3 characters")
	private String name;
	@Email(message = "not a valid Email address")
	@NotEmpty(message="email is required")
	private String email;
	@NotEmpty
	@Size(min = 2,max = 10,message = "password must be of length 2 to 10")
	private String password;
	@NotEmpty
	private String about;
	private Set<RoleDto> roles = new HashSet<>();

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
}
