package com.example.service;

import com.example.payload.BookingDto;
import com.example.payload.UpdateBookingDto;

public interface BookingService {

	void deleteBooking(Integer bookingId);

	BookingDto createBooking(BookingDto bookingDto, Integer userId, Integer coachId);

	UpdateBookingDto updateBooking(Integer bookingId,UpdateBookingDto updatebookingDto);
}
