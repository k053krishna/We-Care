package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Booking;
import com.example.entity.Coach;
import com.example.entity.User;
import com.example.exception.CoachAuthenticationException;
import com.example.exception.CoachNotFoundException;
import com.example.exception.UserAuthenticationException;
import com.example.payload.BookingDto;
import com.example.payload.CoachProfileDto;
import com.example.payload.UserProfileDto;
import com.example.repositories.BookingRepo;
import com.example.repositories.CoachRepo;
import com.example.service.CoachService;

@Service
public class CoachServiceImpl implements CoachService {

	@Autowired
	private CoachRepo coachRepo;
	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Coach createCoach(Coach coach) {
		Coach savedCoach = coachRepo.save(coach);
		return savedCoach;
	}

	@Override
	public Optional<Coach> loginCoach(String email, String password) {
		Optional<Coach> emailandPassword = coachRepo.findByNameAndPassword(email, password);

		if (emailandPassword.isPresent()) {
			return emailandPassword;
		} else {
			throw new CoachAuthenticationException("Invalid email or password");
		}
	}

	@Override
	public CoachProfileDto getCoach(Integer id) {
		Optional<Coach> userEntity = coachRepo.findById(id);

		if (userEntity.isPresent()) {
			return coachToCoachProfileDto(userEntity.get());
		} else {
			throw new UserAuthenticationException("Invalid User Id");
		}
	}

	@Override
	public List<CoachProfileDto> getAllCoach() {
		List<Coach> allCoach = coachRepo.findAll();
		List<CoachProfileDto> collectedCoach = allCoach.stream().map(coach -> coachToCoachProfileDto(coach))
				.collect(Collectors.toList());
		return collectedCoach;
	}

	@Override
	public List<BookingDto> getAllBookingByCoachId(Integer coachId) {
		
		  Optional<Coach> coachOptional = coachRepo.findById(coachId);
		  
		  if (coachOptional.isPresent()) {
	            Coach coach = coachOptional.get();
	            List<Booking> allCoachBookings = bookingRepo.findAllByCoach(coach);
	            
		return allCoachBookings.stream().map(this::BookingToBookingDto).collect(Collectors.toList());
		  } else {
	            // Coach with the given ID not found
	            throw new CoachNotFoundException("Coach with ID " + coachId + " not found");
	        }	  
	}
		  
		  
	public CoachProfileDto coachToCoachProfileDto(Coach coach) {
		CoachProfileDto coachProfileDto = modelMapper.map(coach, CoachProfileDto.class);
		return coachProfileDto;
	}

	public Coach coachProfileDtoToCoach(CoachProfileDto coachProfileDto) {
		Coach coach = this.modelMapper.map(coachProfileDto, Coach.class);
		return coach;
	}
	
	public BookingDto BookingToBookingDto(Booking booking) {

		BookingDto bookingDto = modelMapper.map(booking, BookingDto.class);
		return bookingDto;
	}

}
