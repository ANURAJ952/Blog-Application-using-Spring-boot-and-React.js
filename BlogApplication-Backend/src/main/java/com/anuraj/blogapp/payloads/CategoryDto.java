package com.anuraj.blogapp.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
     
	private int categoryId;
	@NotEmpty
	@Size(min = 4,max = 20,message = "length not in a rangr of 4 to 20")
	private String categoryTitle;
	@NotEmpty
	@Size(min = 0,max = 100,message = "length not in a rangr of 0 to 20")
	private String categoryDescription;
	
}
