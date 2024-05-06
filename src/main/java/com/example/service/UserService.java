package com.example.service;

import java.util.Optional;

import com.example.entity.User;
import com.example.payload.UserDto;
import com.example.payload.UserProfileDto;


public interface UserService {

	User createUser(User user);
	
	Optional<User> loginUser(String email, String password);
	
	UserProfileDto getUser(Integer id);
}
