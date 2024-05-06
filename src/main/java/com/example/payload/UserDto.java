package com.example.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min=4 ,message="UserName must be min of 4 charecter !!")
	private String name;
	@NotEmpty
	@Size(min=3,max=10,message="Password must be minimum of 3 chars and max of 10 chars !!")
	//@Parent
	private String password;
	@Email(message="Email is not valid !!")
	private String email;
	
}