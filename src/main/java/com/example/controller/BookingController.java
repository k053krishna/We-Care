package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payload.ApiResponse;
import com.example.payload.BookingDto;
import com.example.payload.UpdateBookingDto;
import com.example.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/users/{userId}/booking/{coachId}")
	public ResponseEntity<BookingDto> bookAnAppointment(@RequestBody BookingDto bookingDto,
			@PathVariable Integer userId, @PathVariable Integer coachId) {
		BookingDto createBooking = bookingService.createBooking(bookingDto, userId,coachId);

		return new ResponseEntity(createBooking,HttpStatus.CREATED);
	}
	
	@PutMapping("/booking/{bookingId}")
	public ResponseEntity<UpdateBookingDto> resheduleAnAppointment(@PathVariable Integer bookingId,
			@RequestBody UpdateBookingDto updatebookingDto){
		UpdateBookingDto updatedBooking = bookingService.updateBooking(bookingId, updatebookingDto);
		return new ResponseEntity(updatedBooking,HttpStatus.OK);
	}
	
	@DeleteMapping("/booking/{bookingId}")
	public ApiResponse deleteBooking(@PathVariable Integer bookingId) {

		this.bookingService.deleteBooking(bookingId);
		return new ApiResponse("booking is SuccessFully Delete !!", true);
	} 
}
