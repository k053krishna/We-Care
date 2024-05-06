package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.payload.UserDto;
import com.example.payload.UserProfileDto;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private  UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
	    Optional<User> userOptional = userService.loginUser(user.getEmail(), user.getPassword());
	    
	    String email = userOptional.get().getEmail();
	    
	    String password = userOptional.get().getPassword();
	    
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(email);
	    		
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Integer userId){
		System.out.println("id...." +userId);
		UserProfileDto userProfileDto = userService.getUser(userId);
		System.out.println("userprofileDto" +userProfileDto);
		return ResponseEntity.ok(userProfileDto);
	}

}
