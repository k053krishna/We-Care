package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Coach;
import com.example.entity.User;
import com.example.payload.BookingDto;
import com.example.payload.CoachProfileDto;
import com.example.service.CoachService;

@RestController
@RequestMapping("/api/coach")
public class CoachController {


	@Autowired
	private  CoachService coachService;
	
	@PostMapping("/users")
	public ResponseEntity<Coach> createUser(@RequestBody Coach coach){
		Coach createdCoach = coachService.createCoach(coach);
		return new ResponseEntity<>(createdCoach,HttpStatus.CREATED);
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
	    Optional<Coach> coachOptional = coachService.loginCoach(user.getName(), user.getPassword());
	    
	    String name = coachOptional.get().getName();
	    
	    String password = coachOptional.get().getPassword();
	    
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(name);
	    		
	}
	
	@GetMapping("/coach/{coachId}")
	public ResponseEntity<CoachProfileDto> getUserProfile(@PathVariable Integer coachId){
		
		CoachProfileDto coachProfileDto = coachService.getCoach(coachId);
		
		return ResponseEntity.ok(coachProfileDto);
	}
	
	@GetMapping("/coach/all")
	public ResponseEntity<List<CoachProfileDto>> allCoachDetails(){
		List<CoachProfileDto> allCoach = coachService.getAllCoach();
		return ResponseEntity.ok(allCoach);
	}
	
	@GetMapping("/coaches/booking/{coachId}")
	public ResponseEntity<List<BookingDto>> getBookingDetails(@PathVariable Integer coachId){
		List<BookingDto> allBookingByCoachId = coachService.getAllBookingByCoachId(coachId);
		return ResponseEntity.ok(allBookingByCoachId);
	}
}
