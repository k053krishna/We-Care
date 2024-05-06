package com.example.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Booking;
import com.example.entity.Coach;
import com.example.entity.User;
import com.example.exception.BookingAuthenticationException;
import com.example.exception.CoachNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.payload.BookingDto;
import com.example.payload.UpdateBookingDto;
import com.example.repositories.BookingRepo;
import com.example.repositories.CoachRepo;
import com.example.repositories.UserRepo;
import com.example.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CoachRepo coachRepo;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public BookingDto createBooking(BookingDto bookingDto, Integer userId, Integer coachId) {

		// Check if user exists
		Optional<User> userOptional = userRepo.findById(userId);
		if (userOptional.isEmpty()) {
			throw new UserNotFoundException("User with id " + userId + " does not exist");
		}

		// Check if coach exists
		Optional<Coach> coachOptional = coachRepo.findById(coachId);
		if (coachOptional.isEmpty()) {
			throw new CoachNotFoundException("Coach with id " + coachId + " does not exist");
		}

		// Create Booking entity from BookingDto
		Booking booking = BookingDtoToBooking(bookingDto);
		booking.setUser(userOptional.get());
		booking.setCoach(coachOptional.get());

		// Save the booking entity
		Booking savedBooking = bookingRepo.save(booking);

		// Convert the saved booking entity to BookingDto and return
		return this.BookingToBookingDto(savedBooking);
	}

	@Override
	public void deleteBooking(Integer bookingId) {

		Optional<Booking> bookingOptional = bookingRepo.findById(bookingId);
		
		if (bookingOptional.isPresent()) {
		
			Booking booking = bookingOptional.get();

			// Delete booking
			bookingRepo.delete(booking);
		} else {
			throw new BookingAuthenticationException("Booking with id " + bookingId + " does not exist");

		}

		/*
		 * // Check if associated user has no more bookings and delete if necessary User
		 * user = booking.getUser(); if (user != null && user.getBooking().isEmpty()) {
		 * //userRepo.delete(user); }
		 * 
		 * // Check if associated coach has no more bookings and delete if necessary
		 * Coach coach = booking.getCoach(); if (coach != null &&
		 * coach.getBooking().isEmpty()) { //coachRepo.delete(coach); }
		 */
	}

	@Override
	public UpdateBookingDto updateBooking(Integer bookingId, UpdateBookingDto updatebookingDto) {

		Optional<Booking> bookingOptional = bookingRepo.findById(bookingId);

		if (bookingOptional.isPresent()) {
			Booking booking = bookingOptional.get();
			booking.setDate_of_birth(updatebookingDto.getDate_of_birth().now());
			booking.setSlot(updatebookingDto.getSlot());
			// Save the updated booking
			Booking updatedBooking = bookingRepo.save(booking);

			// Convert the updated booking entity to BookingDto and return
			return this.bookingToUpdateBookingDto(updatedBooking);
		} else {
			throw new BookingAuthenticationException("Booking with id " + bookingId + " does not exist");
		}
	}

	public BookingDto BookingToBookingDto(Booking booking) {

		BookingDto bookingDto = modelmapper.map(booking, BookingDto.class);
		return bookingDto;
	}

	public Booking BookingDtoToBooking(BookingDto bookingDto) {
		Booking booking = modelmapper.map(bookingDto, Booking.class);
		return booking;
	}

	public UpdateBookingDto bookingToUpdateBookingDto(Booking booking) {
		UpdateBookingDto updateBookingDto = modelmapper.map(booking, UpdateBookingDto.class);
		return updateBookingDto;
	}

}
