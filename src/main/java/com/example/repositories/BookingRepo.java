package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Booking;
import com.example.entity.Coach;
import com.example.payload.BookingDto;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

	 List<Booking> findAllByCoach(Coach coach);

}
