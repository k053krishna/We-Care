package com.example.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.exception.UserAuthenticationException;
import com.example.payload.UserDto;
import com.example.payload.UserProfileDto;
import com.example.repositories.UserRepo;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User createUser(User user) {
		User userEntity = userRepo.save(user);
		return userEntity;
	}

	@Override
	public Optional<User> loginUser(String email, String password) {
		Optional<User> emailandPassword = userRepo.findByEmailAndPassword(email, password);

		if (emailandPassword.isPresent()) {
			return emailandPassword;
		} else {
			throw new UserAuthenticationException("Invalid email or password");
		}
	}

	@Override
	public UserProfileDto getUser(Integer id) {
		
		Optional<User> userEntity = userRepo.findById(id);
		
		if (userEntity.isPresent()) {
			return userToUserProfileDto(userEntity.get());
		} else {
			throw new UserAuthenticationException("Invalid User Id");
		}
	}

	
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		/*
		 * User user = new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setAbout(userDto.getAbout());
		 * user.setEmail(userDto.getEmail()); user.setPassword(userDto.getPassword());
		 */
		return user;
	}

	public UserDto userToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		/*
		 * UserDto userDto = new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setAbout(user.getAbout());
		 * userDto.setEmail(user.getEmail()); userDto.setPassword(user.getPassword());
		 */
		return userDto;
	}

	public UserProfileDto userToUserProfileDto(User user) {
		UserProfileDto userProfileDto = this.modelMapper.map(user, UserProfileDto.class);
		System.out.println(userProfileDto);
		return userProfileDto;
	}
	
	public User userProfileDtoToUser(UserProfileDto userProfileDto) {
		User user = this.modelMapper.map(userProfileDto, User.class);
		return user;
	}

}
