package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Coach;
import com.example.payload.BookingDto;
import com.example.payload.CoachProfileDto;


public interface CoachService {

	Coach createCoach(Coach coach);
	
	Optional<Coach> loginCoach(String email, String password);
	
	CoachProfileDto getCoach(Integer id);
	
	List<CoachProfileDto> getAllCoach();

	List<BookingDto> getAllBookingByCoachId(Integer coachId);

}
