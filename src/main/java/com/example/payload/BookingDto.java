package com.example.payload;

import java.time.LocalDate;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingDto {

	private int booking_Id;
	private int user_Id;
	private int coach_Id;
	private LocalDate date_of_birth=LocalDate.now();
	private String slot;
}
